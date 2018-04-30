package org.inzy.framework.web.demo.controller.test;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.InzyNoteEntity;
import org.inzy.framework.web.demo.service.test.InzyNoteServiceI;
import org.inzy.framework.web.system.pojo.base.TSDepart;
import org.inzy.framework.web.system.service.SystemService;


/**   
 * @Title: Controller
 * @Description: 公告
 * @author Goodman Zhang
 * @date 2013-03-12 14:06:34
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/inzyNoteController")
public class InzyNoteController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InzyNoteController.class);

	@Autowired
	private InzyNoteServiceI inzyNoteService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 公告列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "inzyNote")
	public ModelAndView inzyNote(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/test/inzyNoteList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(InzyNoteEntity inzyNote,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InzyNoteEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, inzyNote);
		this.inzyNoteService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除公告
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InzyNoteEntity inzyNote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		inzyNote = systemService.getEntity(InzyNoteEntity.class, inzyNote.getId());
		message = "删除成功";
		inzyNoteService.delete(inzyNote);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加公告
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(InzyNoteEntity inzyNote, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(inzyNote.getId())) {
			message = "更新成功";
			inzyNoteService.saveOrUpdate(inzyNote);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			inzyNoteService.save(inzyNote);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 公告列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InzyNoteEntity inzyNote, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(inzyNote.getId())) {
			inzyNote = inzyNoteService.getEntity(InzyNoteEntity.class, inzyNote.getId());
			req.setAttribute("inzyNotePage", inzyNote);
		}
		return new ModelAndView("inzy/demo/test/inzyNote");
	}
	/**
	 * 公告列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "addorupdateNoBtn")
	public ModelAndView addorupdateNoBtn(InzyNoteEntity inzyNote, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(inzyNote.getId())) {
			inzyNote = inzyNoteService.getEntity(InzyNoteEntity.class, inzyNote.getId());
			req.setAttribute("inzyNotePage", inzyNote);
		}
		return new ModelAndView("inzy/demo/test/inzyNoteNoBtn");
	}
}
