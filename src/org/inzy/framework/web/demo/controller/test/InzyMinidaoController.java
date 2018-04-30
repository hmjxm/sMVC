package org.inzy.framework.web.demo.controller.test;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.InzyMinidaoEntity;
import org.inzy.framework.web.demo.service.test.InzyMinidaoServiceI;
import org.inzy.framework.web.system.pojo.base.TSDepart;
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
 * @Description: Minidao例子
 * @author fancq
 * @date 2013-12-23 21:18:59
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/inzyMinidaoController")
public class InzyMinidaoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InzyMinidaoController.class);

	@Autowired
	private InzyMinidaoServiceI inzyMinidaoService;
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
	 * Minidao例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "inzyMinidao")
	public ModelAndView inzyMinidao(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/test/inzyMinidaoList");
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
	public void datagrid(InzyMinidaoEntity inzyMinidao,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List<InzyMinidaoEntity> list = inzyMinidaoService.listAll(inzyMinidao, dataGrid.getPage(), dataGrid.getRows());
		Integer count = inzyMinidaoService.getCount();
		dataGrid.setTotal(count);
		dataGrid.setResults(list);
		String total_salary = String.valueOf(inzyMinidaoService.getSumSalary());
		/*
		 * 说明：格式为 字段名:值(可选，不写该值时为分页数据的合计) 多个合计 以 , 分割
		 */
		dataGrid.setFooter("salary:"+(total_salary.equalsIgnoreCase("null")?"0.0":total_salary)+",age,email:合计");
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Minidao例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InzyMinidaoEntity inzyMinidao, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		inzyMinidao = inzyMinidaoService.getEntity(InzyMinidaoEntity.class, inzyMinidao.getId());
		message = "Minidao例子删除成功";
		inzyMinidaoService.delete(inzyMinidao);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加Minidao例子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(InzyMinidaoEntity inzyMinidao, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(inzyMinidao.getId())) {
			message = "Minidao例子更新成功";
			InzyMinidaoEntity t = inzyMinidaoService.getEntity(InzyMinidaoEntity.class, inzyMinidao.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(inzyMinidao, t);
				inzyMinidaoService.update(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "Minidao例子更新失败";
			}
		} else {
			message = "Minidao例子添加成功";
			inzyMinidao.setStatus("0");
			inzyMinidaoService.insert(inzyMinidao);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * Minidao例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InzyMinidaoEntity inzyMinidao, HttpServletRequest req) {
		//获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		
		if (StringUtil.isNotEmpty(inzyMinidao.getId())) {
			inzyMinidao = inzyMinidaoService.getEntity(InzyMinidaoEntity.class, inzyMinidao.getId());
			req.setAttribute("inzyMinidaoPage", inzyMinidao);
		}
		return new ModelAndView("inzy/demo/test/inzyMinidao");
	}
}
