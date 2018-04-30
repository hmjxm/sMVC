package com.inzyme.p2p.projects.projectcheck.controller;

import com.inzyme.p2p.members.personalmember.entity.PersonalinfoEntity;
import com.inzyme.p2p.projects.projectcheck.entity.LoanapplyEntity;
import com.inzyme.p2p.projects.projectcheck.service.LoanapplyServiceI;
import com.inzyme.p2p.rules.bizrule.entity.InterestargEntity;
import com.inzyme.p2p.rules.bizrule.entity.InteresttypeEntity;
import com.inzyme.p2p.rules.bizrule.entity.InvestbidtypeEntity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.service.SystemService;

/**
 * @Title: Controller
 * @Description: 融资申请
 * @author onlineGenerator
 * @date 2015-07-04 23:35:45
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/loanapplyController")
public class LoanapplyController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(LoanapplyController.class);

	@Autowired
	private LoanapplyServiceI loanapplyService;
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
	 * 融资申请列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "loanapply")
	public ModelAndView loanapply(HttpServletRequest request) {
		String bidReplace = "";
		List<InvestbidtypeEntity> bidTypeList = systemService
				.getList(InvestbidtypeEntity.class);
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
		return new ModelAndView(
				"com/inzyme/p2p/projects/projectcheck/loanapplyList");
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
	public void datagrid(LoanapplyEntity loanapply, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LoanapplyEntity.class, dataGrid);
		// 查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				loanapply, request.getParameterMap());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.loanapplyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除融资申请
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LoanapplyEntity loanapply, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		loanapply = systemService.getEntity(LoanapplyEntity.class,
				loanapply.getId());
		message = "融资申请删除成功";
		try {
			loanapplyService.delete(loanapply);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "融资申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 修改跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LoanapplyEntity loanapply,
			HttpServletRequest req) {
		String functype = req.getParameter("functype");
		req.setAttribute("functype", functype);

		if (StringUtil.isNotEmpty(loanapply.getId())) {
			loanapply = loanapplyService.getEntity(LoanapplyEntity.class,
					loanapply.getId());
			req.setAttribute("loanapplyPage", loanapply);
		}
		//编辑
		if (functype.equals("2")) {
			String arguments = loanapply.getArgs();
			String inid = loanapply.getInteresttypeid();
			req.setAttribute("arguments", arguments);
			req.setAttribute("inid", inid);
		}
		List<InvestbidtypeEntity> bidTypeList = systemService.findByProperty(InvestbidtypeEntity.class, "status", 1);
		req.setAttribute("bidTypeList", bidTypeList);

		List<InteresttypeEntity> itTypeList = systemService.findByProperty(InteresttypeEntity.class,"status","1");
		req.setAttribute("itTypeList", itTypeList);

		List<PersonalinfoEntity> personTypeList = systemService.findByProperty(PersonalinfoEntity.class,"status","0");
		req.setAttribute("personTypeList", personTypeList);
		return new ModelAndView(
				"com/inzyme/p2p/projects/projectcheck/loanapply");
	}

	/**
	 * 添加
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LoanapplyEntity interesttype,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(interesttype.getId())) {
			message = "融资申请修改成功";
			systemService.saveOrUpdate(interesttype);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			message = "融资申请添加成功";
			interesttype.setApprovalstatus(0);
			systemService.save(interesttype);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		return j;
	}

	/**
	 * 计息参数下拉
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getArg")
	@ResponseBody
	public Object getArg(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String id = StringUtil.getEncodePra(req.getParameter("id"));
		List<InterestargEntity> args = systemService.findByProperty(
				InterestargEntity.class, "interesttypeid", id);
		JSONArray jsonArray = JSONArray.fromObject(args);
		return jsonArray;
	}
}
