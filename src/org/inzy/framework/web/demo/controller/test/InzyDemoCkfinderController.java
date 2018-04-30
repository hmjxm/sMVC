package org.inzy.framework.web.demo.controller.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.InzyDemoCkfinderEntity;
import org.inzy.framework.web.demo.service.test.InzyDemoCkfinderServiceI;
import org.inzy.framework.web.system.service.SystemService;


/**
 * @Title: Controller
 * @Description: ckeditor+ckfinder例子
 * @author Alexander
 * @date 2013-09-19 18:29:20
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/inzyDemoCkfinderController")
public class InzyDemoCkfinderController extends BaseController {

	@Autowired
	private InzyDemoCkfinderServiceI inzyDemoCkfinderService;
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
	 * ckeditor+ckfinder例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "inzyDemoCkfinder")
	public ModelAndView inzyDemoCkfinder(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/test/inzyDemoCkfinderList");
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
	public void datagrid(InzyDemoCkfinderEntity inzyDemoCkfinder,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InzyDemoCkfinderEntity.class,
				dataGrid);
		// 查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				inzyDemoCkfinder, request.getParameterMap());
		this.inzyDemoCkfinderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除ckeditor+ckfinder例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InzyDemoCkfinderEntity inzyDemoCkfinder,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		inzyDemoCkfinder = systemService.getEntity(
				InzyDemoCkfinderEntity.class, inzyDemoCkfinder.getId());
		message = "ckeditor+ckfinder例子删除成功";
		inzyDemoCkfinderService.delete(inzyDemoCkfinder);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加ckeditor+ckfinder例子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(InzyDemoCkfinderEntity inzyDemoCkfinder,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(inzyDemoCkfinder.getId())) {
			message = "ckeditor+ckfinder例子更新成功";
			InzyDemoCkfinderEntity t = inzyDemoCkfinderService.get(
					InzyDemoCkfinderEntity.class, inzyDemoCkfinder.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(inzyDemoCkfinder, t);
				inzyDemoCkfinderService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "ckeditor+ckfinder例子更新失败";
			}
		} else {
			message = "ckeditor+ckfinder例子添加成功";
			inzyDemoCkfinderService.save(inzyDemoCkfinder);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * ckeditor+ckfinder例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InzyDemoCkfinderEntity inzyDemoCkfinder,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(inzyDemoCkfinder.getId())) {
			inzyDemoCkfinder = inzyDemoCkfinderService.getEntity(
					InzyDemoCkfinderEntity.class, inzyDemoCkfinder.getId());
			req.setAttribute("inzyDemoCkfinderPage", inzyDemoCkfinder);
		}
		return new ModelAndView("inzy/demo/test/inzyDemoCkfinder");
	}
	
	/**
	 * ckeditor+ckfinder例子预览
	 * 
	 * @return
	 */
	@RequestMapping(params = "preview")
	public ModelAndView preview(InzyDemoCkfinderEntity inzyDemoCkfinder,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(inzyDemoCkfinder.getId())) {
			inzyDemoCkfinder = inzyDemoCkfinderService.getEntity(
					InzyDemoCkfinderEntity.class, inzyDemoCkfinder.getId());
			req.setAttribute("ckfinderPreview", inzyDemoCkfinder);
		}
		return new ModelAndView("inzy/demo/test/inzyDemoCkfinderPreview");
	}
}
