package com.inzyme.p2p.rules.bizrule.controller;
import com.inzyme.p2p.rules.bizrule.entity.InvestbidtypeEntity;
import com.inzyme.p2p.rules.bizrule.service.InvestbidtypeServiceI;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.FileMeta;
import org.inzy.framework.web.demo.entity.test.InzyDemo;
import org.inzy.framework.web.system.service.SystemService;
import org.inzy.framework.core.util.MyBeanUtils;





/**   
 * @Title: Controller
 * @Description: 投资标的种类
 * @author onlineGenerator
 * @date 2015-07-04 18:06:09
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/investbidtypeController")
public class InvestbidtypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InvestbidtypeController.class);

	@Autowired
	private InvestbidtypeServiceI investbidtypeService;
	@Autowired
	private SystemService systemService;
	
	LinkedList<FileMeta> icon = new LinkedList<FileMeta>();
	LinkedList<FileMeta> title = new LinkedList<FileMeta>();
	FileMeta fileMeta = null;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 投资标的种类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "investbidtype")
	public ModelAndView investbidtype(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/p2p/rules/bizrule/investbidtypeList");
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
	public void datagrid(InvestbidtypeEntity investbidtype,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InvestbidtypeEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, investbidtype, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.investbidtypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 *修改跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InvestbidtypeEntity investbidtype, HttpServletRequest req) {
		// 功能分类：1-新增、2-修改、3-查看
		String functype = req.getParameter("functype");
		req.setAttribute("functype", functype);
		if (StringUtil.isNotEmpty(investbidtype.getId())) {
		investbidtype = investbidtypeService.getEntity(InvestbidtypeEntity.class, investbidtype.getId());
		req.setAttribute("investbidtypePage", investbidtype);
		
	}
	return new ModelAndView("com/inzyme/p2p/rules/bizrule/investbidtype");
	}

	/**
	 * 保存
	 */
	@RequestMapping(params = "saveInvest")
	@ResponseBody
	public AjaxJson saveInvest(InvestbidtypeEntity investbidtype, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(investbidtype.getId())) {
			message = "投资标的种类修改成功";
			systemService.saveOrUpdate(investbidtype);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "投资标的种类添加成功";
			systemService.save(investbidtype);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		return j;
	}
	/**
	 * 删除投资标的种类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(InvestbidtypeEntity investbidtype, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		investbidtype = systemService.getEntity(InvestbidtypeEntity.class, investbidtype.getId());
		message = "投资标的种类删除成功";
		try{
			investbidtypeService.delete(investbidtype);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "投资标的种类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params = "updatestatus")
	@ResponseBody
	public AjaxJson updatestatus(InvestbidtypeEntity investbidtype, HttpServletRequest request) {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(investbidtype.getId())) {
			message = "启用成功";
			InvestbidtypeEntity t =investbidtypeService.get(InvestbidtypeEntity.class, id);
			try {
				if(flag.equals("1")){
					message = "启用成功";
					t.setStatus(1);
				}
				if(flag.equals("2")){
					message = "禁用成功";
					t.setStatus(0);
				}
				investbidtypeService.saveOrUpdate(t);
				j.setMsg(message);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return j;
	}
	
	@RequestMapping(params = "upload", method = RequestMethod.POST)
	@ResponseBody
	public LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			try {
				String path ="upload/";
				fileMeta.setBytes(mpf.getBytes());
				String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + path ;// 文件的硬盘真实路径
				String savePath = realPath + mpf.getOriginalFilename();// 文件保存全路径
				FileCopyUtils.copy(mpf.getBytes(),new File(savePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			icon.add(fileMeta);
		}
		return icon;
	}

	@RequestMapping(params = "titleUpload", method = RequestMethod.POST)
	@ResponseBody
	public LinkedList<FileMeta> titleUpload(MultipartHttpServletRequest request, HttpServletResponse response) {
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			try {
				String path ="upload/";
				fileMeta.setBytes(mpf.getBytes());
				String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + path ;// 文件的硬盘真实路径
				String savePath = realPath + mpf.getOriginalFilename();// 文件保存全路径
				FileCopyUtils.copy(mpf.getBytes(),new File(savePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			title.add(fileMeta);
		}
		return title;
	}
}
