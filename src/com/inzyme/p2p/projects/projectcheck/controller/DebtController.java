package com.inzyme.p2p.projects.projectcheck.controller;
import com.inzyme.p2p.members.personalmember.entity.PersonalinfoEntity;
import com.inzyme.p2p.projects.projectcheck.entity.DebtEntity;
import com.inzyme.p2p.projects.projectcheck.service.DebtServiceI;
import com.inzyme.p2p.rules.bizrule.entity.InvestbidtypeEntity;

import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tools.ant.types.selectors.DepthSelector;
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
import org.inzy.framework.core.util.oConvertUtils;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.service.SystemService;



/**   
 * @Title: Controller
 * @Description: 债权表
 * @author Auto-generator
 * @date 2015-07-14 10:36:43
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/debtController")
public class DebtController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DebtController.class);

	@Autowired
	private DebtServiceI debtService;
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
	 * 债权表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "debt")
	public ModelAndView debt(HttpServletRequest request) {
	
		return new ModelAndView("com/inzyme/p2p/projects/projectcheck/debtList");
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
	public void datagrid(DebtEntity debt,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DebtEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, debt, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.debtService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除债权表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(DebtEntity debt, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		debt = systemService.getEntity(DebtEntity.class, debt.getId());
		message = "删除成功";
		try{
			debtService.delete(debt);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 操作列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateop")
	public ModelAndView addorupdateop(DebtEntity debt,
			HttpServletRequest req) {
		if (debt.getId() != null) {
			debt = systemService.getEntity(DebtEntity.class,
					debt.getId());
			req.setAttribute("debt", debt);
		}
		String functionId = oConvertUtils.getString(req
				.getParameter("functionId"));
		req.setAttribute("functionId", functionId);
		List<PersonalinfoEntity> personTypeList = systemService.findByProperty(PersonalinfoEntity.class,"status","0");
		req.setAttribute("personTypeList", personTypeList);
		return new ModelAndView("com/inzyme/p2p/projects/projectcheck/debt");
	}

	
	@RequestMapping(params = "projectid")
	public void projectid(HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DebtEntity.class, dataGrid);
		String investId = oConvertUtils.getString(request.getParameter("functionId"));
		cq.eq("investproject.id", investId);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	

	/**
	 * 操作录入
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveop")
	@ResponseBody
	public AjaxJson saveop(DebtEntity debt, HttpServletRequest request) {
		String pid = request.getParameter("investproject.id");
		if (pid.equals("")) {
			debt.setInvestproject(null);
		}
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(debt.getId())) {
			message = "更新成功";
			systemService.saveOrUpdate(debt);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			systemService.save(debt);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);

		}

		return j;
	}
	
	@RequestMapping(params = "updatestatus")
	@ResponseBody
	public AjaxJson updatestatus(DebtEntity debt, HttpServletRequest request) {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(debt.getId())) {
			message = "启用成功";
			DebtEntity t =debtService.get(DebtEntity.class, id);
			try {
				if(flag.equals("1")){
					message = "启用成功";
					t.setStatus(1);
				}
				if(flag.equals("2")){
					message = "禁用成功";
					t.setStatus(0);
				}
				debtService.saveOrUpdate(t);
				j.setMsg(message);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return j;
	}
}
