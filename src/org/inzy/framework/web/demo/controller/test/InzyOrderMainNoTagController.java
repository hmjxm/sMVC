package org.inzy.framework.web.demo.controller.test;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.InzyOrderCustomEntity;
import org.inzy.framework.web.demo.entity.test.InzyOrderMainEntity;
import org.inzy.framework.web.demo.entity.test.InzyOrderProductEntity;
import org.inzy.framework.web.demo.page.InzyOrderMainPage;
import org.inzy.framework.web.demo.service.test.InzyOrderMainServiceI;
import org.inzy.framework.web.system.service.SystemService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller
 * @Description: 订单信息
 * @author Goodman Zhang
 * @date 2013-03-19 22:01:34
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/inzyOrderMainNoTagController")
public class InzyOrderMainNoTagController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InzyOrderMainController.class);

	@Autowired
	private InzyOrderMainServiceI inzyOrderMainService;
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
	 * 订单信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "inzyOrderMainNoTag")
	public ModelAndView inzyOrderMain(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/notag/inzyOrderMainListNoTag");
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
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InzyOrderMainEntity.class, dataGrid);
		this.inzyOrderMainService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除订单信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InzyOrderMainEntity inzyOrderMain, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		inzyOrderMain = systemService.getEntity(InzyOrderMainEntity.class, inzyOrderMain.getId());
		message = "删除成功";
		inzyOrderMainService.delMain(inzyOrderMain);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订单及明细信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(InzyOrderMainEntity inzyOrderMain ,InzyOrderMainPage inzyOrderMainPage,	
			HttpServletRequest request) {
		List<InzyOrderProductEntity> inzyOrderProducList =  inzyOrderMainPage.getInzyOrderProductList();
		List<InzyOrderCustomEntity>  inzyOrderCustomList = inzyOrderMainPage.getInzyOrderCustomList();
		Boolean inzyOrderCustomShow = "true".equals(request.getParameter("inzyOrderCustomShow"));
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(inzyOrderMain.getId())) {
			message = "更新成功";
			inzyOrderMainService.updateMain(inzyOrderMain, inzyOrderProducList, inzyOrderCustomList,inzyOrderCustomShow);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			inzyOrderMainService.addMain(inzyOrderMain, inzyOrderProducList, inzyOrderCustomList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 订单信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateNoTag")
	public ModelAndView addorupdate(InzyOrderMainEntity inzyOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(inzyOrderMain.getId())) {
			inzyOrderMain = inzyOrderMainService.getEntity(InzyOrderMainEntity.class, inzyOrderMain.getId());
			req.setAttribute("inzyOrderMainPage", inzyOrderMain);
		}
		if (StringUtil.isNotEmpty(inzyOrderMain.getGoOrderCode())) {
			List<InzyOrderProductEntity> inzyOrderProductEntityList =  inzyOrderMainService.findByProperty(InzyOrderProductEntity.class, "goOrderCode", inzyOrderMain.getGoOrderCode());
			req.setAttribute("inzyOrderProductList", inzyOrderProductEntityList);
		}
		if (StringUtil.isNotEmpty(inzyOrderMain.getGoOrderCode())) {
			List<InzyOrderCustomEntity> inzyInzyOrderCustomEntityList =  inzyOrderMainService.findByProperty(InzyOrderCustomEntity.class, "goOrderCode", inzyOrderMain.getGoOrderCode());
			req.setAttribute("inzyOrderCustomList", inzyInzyOrderCustomEntityList);
		}
		return new ModelAndView("inzy/demo/notag/inzyOrderMainNoTag");
	}
}
