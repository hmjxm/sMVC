package org.inzy.framework.web.demo.controller.test;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.InzyJdbcEntity;
import org.inzy.framework.web.demo.service.test.InzyJdbcServiceI;
import org.inzy.framework.web.system.service.SystemService;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller
 * @Description: 通过JDBC访问数据库
 * @author Quainty
 * @date 2013-05-20 13:18:38
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/inzyJdbcController")
public class InzyJdbcController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InzyJdbcController.class);

	@Autowired
	private InzyJdbcServiceI inzyJdbcService;
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
	 * 通过JDBC访问数据库列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "inzyJdbc")
	public ModelAndView inzyJdbc(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/test/inzyJdbcList");
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
	public void datagrid(InzyJdbcEntity inzyJdbc,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		// 方式1, 用底层自带的方式往对象中设值  -------------------
		/*
		this.inzyJdbcService.getDatagrid1(inzyJdbc, dataGrid);
		TagUtil.datagrid(response, dataGrid);
		// end of 方式1 ========================================= */ 
		/*
		this.inzyJdbcService.getDatagrid2(inzyJdbc, dataGrid);
		TagUtil.datagrid(response, dataGrid);
		// end of 方式2 ========================================= */ 
		//*
		JSONObject jObject = this.inzyJdbcService.getDatagrid3(inzyJdbc, dataGrid);
		responseDatagrid(response, jObject);
		// end of 方式3 ========================================= */
	}

	/**
	 * 删除通过JDBC访问数据库
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InzyJdbcEntity inzyJdbc, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		String sql = "delete from inzy_demo where id='" + inzyJdbc.getId() + "'";
		inzyJdbcService.executeSql(sql);

		message = "删除成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加通过JDBC访问数据库
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(InzyJdbcEntity inzyJdbc, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(inzyJdbc.getId())) {
			message = "更新成功";
			InzyJdbcEntity t = inzyJdbcService.get(InzyJdbcEntity.class, inzyJdbc.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(inzyJdbc, t);
				inzyJdbcService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			inzyJdbcService.save(inzyJdbc);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 通过JDBC访问数据库列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InzyJdbcEntity inzyJdbc, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(inzyJdbc.getId())) {
			inzyJdbc = inzyJdbcService.getEntity(InzyJdbcEntity.class, inzyJdbc.getId());
			req.setAttribute("inzyJdbcPage", inzyJdbc);
		}
		return new ModelAndView("inzy/demo/test/inzyJdbc");
	}
	// 以下各函数可以提成共用部件 (Add by Quainty)
	public void responseDatagrid(HttpServletResponse response, JSONObject jObject) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw=response.getWriter();
			pw.write(jObject.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	@RequestMapping(params = "dictParameter")
	public String dictParameter(){
		return "inzy/demo/base/jdbc/jdbc-list";
	}
	
	/**
	 * JDBC DEMO 显示列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "listAllDictParaByJdbc")
	public void listAllDictParaByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		this.inzyJdbcService.listAllByJdbc(dataGrid);
		TagUtil.datagrid(response, dataGrid);
	}
}
