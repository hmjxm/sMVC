package com.inzyme.p2p.risks.operaterisk.controller;
import com.inzyme.p2p.risks.operaterisk.entity.CpensatoryappEntity;
import com.inzyme.p2p.risks.operaterisk.service.CpensatoryappServiceI;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.pojo.base.TSDepart;
import org.inzy.framework.web.system.service.SystemService;
import org.inzy.framework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.inzy.framework.core.util.BrowserUtils;
import org.inzy.framework.poi.excel.ExcelExportUtil;
import org.inzy.framework.poi.excel.ExcelImportUtil;
import org.inzy.framework.poi.excel.entity.ExportParams;
import org.inzy.framework.poi.excel.entity.ImportParams;
import org.inzy.framework.poi.excel.entity.TemplateExportParams;
import org.inzy.framework.poi.excel.entity.vo.NormalExcelConstants;
import org.inzy.framework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.inzy.framework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.inzy.framework.core.util.ExceptionUtil;



/**   
 * @Title: Controller
 * @Description: 逾期垫付申请
 * @author Auto-generator
 * @date 2015-07-15 17:52:52
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/cpensatoryappController")
public class CpensatoryappController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CpensatoryappController.class);

	@Autowired
	private CpensatoryappServiceI cpensatoryappService;
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
	 * 逾期垫付申请列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "cpensatoryapp")
	public ModelAndView cpensatoryapp(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/p2p/risks/operaterisk/cpensatoryappList");
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
	public void datagrid(CpensatoryappEntity cpensatoryapp,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CpensatoryappEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cpensatoryapp, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cpensatoryappService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除逾期垫付申请
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CpensatoryappEntity cpensatoryapp, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cpensatoryapp = systemService.getEntity(CpensatoryappEntity.class, cpensatoryapp.getId());
		message = "逾期垫付申请删除成功";
		try{
			cpensatoryappService.delete(cpensatoryapp);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "逾期垫付申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除逾期垫付申请
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "逾期垫付申请删除成功";
		try{
			for(String id:ids.split(",")){
				CpensatoryappEntity cpensatoryapp = systemService.getEntity(CpensatoryappEntity.class, 
				id
				);
				cpensatoryappService.delete(cpensatoryapp);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "逾期垫付申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加逾期垫付申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CpensatoryappEntity cpensatoryapp, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "逾期垫付申请添加成功";
		try{
			cpensatoryappService.save(cpensatoryapp);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "逾期垫付申请添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新逾期垫付申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CpensatoryappEntity cpensatoryapp, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "逾期垫付申请更新成功";
		CpensatoryappEntity t = cpensatoryappService.get(CpensatoryappEntity.class, cpensatoryapp.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(cpensatoryapp, t);
			cpensatoryappService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "逾期垫付申请更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 逾期垫付申请新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CpensatoryappEntity cpensatoryapp, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cpensatoryapp.getId())) {
			cpensatoryapp = cpensatoryappService.getEntity(CpensatoryappEntity.class, cpensatoryapp.getId());
			req.setAttribute("cpensatoryappPage", cpensatoryapp);
		}
		return new ModelAndView("com/inzyme/p2p/risks/operaterisk/cpensatoryapp-add");
	}
	/**
	 * 逾期垫付申请编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CpensatoryappEntity cpensatoryapp, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cpensatoryapp.getId())) {
			cpensatoryapp = cpensatoryappService.getEntity(CpensatoryappEntity.class, cpensatoryapp.getId());
			req.setAttribute("cpensatoryappPage", cpensatoryapp);
		}
		return new ModelAndView("com/inzyme/p2p/risks/operaterisk/cpensatoryapp-update");
	}
	/**
	 * 获取债权表ID
	 * 
	 * @return
	 */
	@RequestMapping(params = "getdebtid")
	public AjaxJson getdebtid(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "";
		
		j.setMsg(message);
		return j;
	}
	
	
}
