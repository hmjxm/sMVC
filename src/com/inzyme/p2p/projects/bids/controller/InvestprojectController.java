package com.inzyme.p2p.projects.bids.controller;
import java.util.List;

import com.inzyme.p2p.members.personalmember.entity.PersonalinfoEntity;
import com.inzyme.p2p.projects.bids.entity.InvestprojectEntity;
import com.inzyme.p2p.projects.bids.service.InvestprojectServiceI;
import com.inzyme.p2p.projects.projectcheck.entity.DebtEntity;
import com.inzyme.p2p.rules.bizrule.entity.InterestargEntity;
import com.inzyme.p2p.rules.bizrule.entity.InvestbidtypeEntity;

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
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.core.util.oConvertUtils;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.service.SystemService;
import org.inzy.framework.core.util.MyBeanUtils;





/**   
 * @Title: Controller
 * @Description: 投资项目表
 * @author Auto-generator
 * @date 2015-07-14 10:35:25
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/investprojectController")
public class InvestprojectController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InvestprojectController.class);

	@Autowired
	private InvestprojectServiceI investprojectService;
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
	 * 投资项目表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "investproject")
	public ModelAndView investproject(HttpServletRequest request) {
		String bidReplace = "";
		List<InvestbidtypeEntity> bidTypeList = systemService.getList(InvestbidtypeEntity.class);
		for (InvestbidtypeEntity bid : bidTypeList) {
			if (bidReplace.length() > 0) {
				bidReplace += ",";
			}
			bidReplace += bid.getBidtypename() + "_" + bid.getId();
		}
		request.setAttribute("bidReplace", bidReplace);

		String argReplace = "";
		List<InterestargEntity> argTypeList = systemService
				.getList(InterestargEntity.class);
		for (InterestargEntity arg : argTypeList) {
			if (argReplace.length() > 0) {
				argReplace += ",";
			}
			argReplace += arg.getArgnames() + "_" + arg.getInteresttypeid();
		}
		request.setAttribute("argReplace", argReplace);

		String personReplace = "";
		List<PersonalinfoEntity> personTypeList = systemService
				.getList(PersonalinfoEntity.class);
		for (PersonalinfoEntity person : personTypeList) {
			if (personReplace.length() > 0) {
				personReplace += ",";
			}
			personReplace += person.getFullname() + "_" + person.getId();
		}
		request.setAttribute("personReplace", personReplace);
		return new ModelAndView("com/inzyme/p2p/projects/bids/investprojectList");
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
	public void datagrid(InvestprojectEntity investproject,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InvestprojectEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, investproject, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.investprojectService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除投资项目表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(InvestprojectEntity investproject, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		investproject = systemService.getEntity(InvestprojectEntity.class, investproject.getId());
		message = "投资项目表删除成功";
		try{
			investprojectService.delete(investproject);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "投资项目表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除投资项目表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "投资项目表删除成功";
		try{
			for(String id:ids.split(",")){
				InvestprojectEntity investproject = systemService.getEntity(InvestprojectEntity.class, 
				id
				);
				investprojectService.delete(investproject);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "投资项目表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加投资项目表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(InvestprojectEntity investproject, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "投资项目表添加成功";
		try{
			investprojectService.save(investproject);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "投资项目表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新投资项目表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(InvestprojectEntity investproject, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "投资项目表更新成功";
		InvestprojectEntity t = investprojectService.get(InvestprojectEntity.class, investproject.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(investproject, t);
			investprojectService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "投资项目表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 投资项目表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(InvestprojectEntity investproject, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(investproject.getId())) {
			investproject = investprojectService.getEntity(InvestprojectEntity.class, investproject.getId());
			req.setAttribute("investprojectPage", investproject);
		}
		return new ModelAndView("com/inzyme/p2p/projects/bids/investproject");
	}
	/**
	 * 投资项目表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(InvestprojectEntity investproject, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(investproject.getId())) {
			investproject = investprojectService.getEntity(InvestprojectEntity.class, investproject.getId());
			req.setAttribute("investprojectPage", investproject);
		}
		return new ModelAndView("com/inzyme/p2p/projects/bids/investproject");
	}
	
	/**
	 * 操作列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "operation")
	public ModelAndView operation(HttpServletRequest request, String functionId) {
		request.setAttribute("functionId", functionId);
		String personReplace = "";
		List<PersonalinfoEntity> personTypeList = systemService
				.getList(PersonalinfoEntity.class);
		for (PersonalinfoEntity person : personTypeList) {
			if (personReplace.length() > 0) {
				personReplace += ",";
			}
			personReplace += person.getFullname() + "_" + person.getId();
		}
		request.setAttribute("personReplace", personReplace);
		return new ModelAndView("com/inzyme/p2p/projects/projectcheck/debtList");
	}
	

}
