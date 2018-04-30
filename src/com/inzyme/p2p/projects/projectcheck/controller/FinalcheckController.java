package com.inzyme.p2p.projects.projectcheck.controller;

import com.inzyme.p2p.members.personalmember.entity.PersonalinfoEntity;
import com.inzyme.p2p.projects.bids.entity.InvestprojectEntity;
import com.inzyme.p2p.projects.bids.service.InvestprojectServiceI;
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
 * @Description: 立项审批
 * @author onlineGenerator
 * @date 2015-07-04 23:35:45
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/finalcheckController")
public class FinalcheckController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(FinalcheckController.class);

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
	@RequestMapping(params = "finalcheck")
	public ModelAndView finalcheck(HttpServletRequest request) {
		//标的种类列表显示
		String bidReplace = "";
		List<InvestbidtypeEntity> bidTypeList = systemService.getList(InvestbidtypeEntity.class);
		for (InvestbidtypeEntity bid : bidTypeList) {
			if (bidReplace.length() > 0) {
				bidReplace += ",";
			}
			bidReplace += bid.getBidtypename() + "_" + bid.getId();
		}
		request.setAttribute("bidReplace", bidReplace);
		//计息参数列表显示
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
		//会员名称列表显示
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
				"com/inzyme/p2p/projects/projectcheck/finalcheckList");
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
		cq.eq("approvalstatus", 0);
		cq.add();
		this.loanapplyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
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
	/**
	 * 审批
	 * 
	 * @return
	 */
	@RequestMapping(params = "upStatus")
	@ResponseBody
	public AjaxJson upStatus(LoanapplyEntity loanapply,InvestprojectEntity investproject, HttpServletRequest request) {
		String id = request.getParameter("id");
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(loanapply.getId())) {
			LoanapplyEntity t = systemService.get(LoanapplyEntity.class, id);
			InvestprojectEntity i = new InvestprojectEntity();
			
			try {
				message = "审批完成";
				t.setApprovalstatus(1);
				loanapplyService.saveOrUpdate(t);
				i.setTitles(t.getTitles());
				i.setBidtypeid(t.getBidtypeid());
				i.setMemberid(t.getMemberid());
				i.setUsages(t.getUsages());
				i.setAmounts(t.getAmounts());
				i.setInteresttypeid(t.getInteresttypeid());
				i.setArgs(t.getArgs());
				i.setReturnterm(t.getReturnterm());
				i.setDescription(t.getDescription());
				i.setInterestrate(t.getRate());
				i.setStatus(t.getApprovalstatus());
				systemService.save(i);
				j.setMsg(message);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return j;
	}
}
