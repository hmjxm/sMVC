package com.inzyme.p2p.finance.account.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.inzyme.p2p.finance.account.entity.AccountEntity;
import com.inzyme.p2p.finance.account.service.AccountServiceI;

/**
 * @Title: Controller
 * @Description: 帐户表
 * @author Auto-generator
 * @date 2015-07-16 10:01:22
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/accountController")
public class AccountController extends BaseController {

	@Autowired
	private AccountServiceI accountService;
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
	 * 帐户表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "account")
	public ModelAndView account(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/p2p/finance/account/accountList");
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
	public void datagrid(AccountEntity account, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AccountEntity.class, dataGrid);
		// 查询条件组装器
		HqlGenerateUtil.installHql(cq, account);
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.accountService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除帐户表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AccountEntity account, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		account = systemService.getEntity(AccountEntity.class, account.getId());
		message = "帐户表删除成功";
		try {
			accountService.delete(account);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "帐户表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除帐户表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "帐户表删除成功";
		try {
			for (String id : ids.split(",")) {
				AccountEntity account = systemService.getEntity(
						AccountEntity.class, id);
				accountService.delete(account);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "帐户表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加帐户表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(AccountEntity account, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(account.getId())) {
			message = "账户: " + account.getAccountname() + "被更新成功";
			systemService.saveOrUpdate(account);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			List<AccountEntity> accList = systemService.findByProperty(
					AccountEntity.class, "accountno", account.getAccountno());
			if (accList.size() > 0) {
				message = "账户: " + account.getAccountno() + "已经存在";
			} else {
				message = "账户: " + account.getAccountno() + "被添加成功";
				systemService.save(account);
				systemService.addLog(message, Globals.Log_Type_INSERT,
						Globals.Log_Leavel_INFO);
			}
		}
		return j;
	}

	/**
	 * 帐户表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AccountEntity account,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(account.getId())) {
			account = accountService.getEntity(AccountEntity.class,
					account.getId());
			req.setAttribute("account", account);
		}
		req.setAttribute("type", req.getParameter("type"));
		return new ModelAndView("com/inzyme/p2p/finance/account/account");
	}
	
	@RequestMapping(params = "saveStatus")
	@ResponseBody
	public AjaxJson saveStatus(String accountId, String status,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		AccountEntity account = new AccountEntity();
		account = systemService.getEntity(AccountEntity.class,accountId);
		account.setStatus(status);
		systemService.saveOrUpdate(account);
		if (status.equals("0")) {
			message = "已冻结";
		} else {
			message = "已启用";
		}
		j.setMsg(message);
		return j;
	}

}
