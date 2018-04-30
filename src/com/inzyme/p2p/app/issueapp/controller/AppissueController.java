package com.inzyme.p2p.app.issueapp.controller;
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
import org.inzy.framework.web.system.pojo.base.TSTypegroup;
import org.inzy.framework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.inzyme.p2p.app.issueapp.entity.AppissueEntity;
import com.inzyme.p2p.app.issueapp.service.AppissueServiceI;



/**   
 * @Title: Controller
 * @Description: APP发布表
 * @author Auto-generator
 * @date 2015-07-15 14:39:02
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/appissueController")
public class AppissueController extends BaseController {

	@Autowired
	private AppissueServiceI appissueService;
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
	 * APP发布表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "appissue")
	public ModelAndView appissue(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/p2p/app/issueapp/appissueList");
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
	public void datagrid(AppissueEntity appissue,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AppissueEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, appissue);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.appissueService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除APP发布表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AppissueEntity appissue, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		appissue = systemService.getEntity(AppissueEntity.class, appissue.getId());
		message = "APP发布表删除成功";
		try{
			appissueService.delete(appissue);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "APP发布表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 删除APP发布表
	 * 
	 * @return
	 */
	@RequestMapping(params = "getAppName")
	@ResponseBody
	public AjaxJson getAppName(String typeGroupCode,String typeCode) {
		AjaxJson j = new AjaxJson();
        TSTypegroup tsg = systemService.findByProperty(TSTypegroup.class, "typegroupcode", typeGroupCode).get(0);
        List type = systemService.findListbySql("select typename from t_s_type where typegroupid = ? and typecode = ?",
        		new Object[]{tsg.getId(),typeCode});
        String appName = type.get(0).toString();
        j.setObj(appName);
		return j;
	}


	/**
	 * 添加APP发布表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(AppissueEntity appissue, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(appissue.getId())) {
			message = "APP: " + appissue.getAppname() + "更新成功";
			systemService.saveOrUpdate(appissue);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			List<AppissueEntity> appList = systemService.findByProperty(AppissueEntity.class, "adtname",appissue.getAppname());
			if (appList.size() > 0) {
				message = "APP: " + appissue.getAppname() + "已经存在";
			} else {
				message = "APP: " + appissue.getAppname() + "添加成功";
				systemService.save(appissue);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * APP发布表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(AppissueEntity appissue, HttpServletRequest req) {
		return new ModelAndView("com/inzyme/p2p/app/issueapp/appissue");
	}
	/**
	 * APP发布表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(AppissueEntity appissue, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(appissue.getId())) {
			appissue = appissueService.getEntity(AppissueEntity.class, appissue.getId());
			req.setAttribute("appissue", appissue);
		}
		return new ModelAndView("com/inzyme/p2p/app/issueapp/appissue");
	}

	
	
	@RequestMapping(params = "saveStatus")
	@ResponseBody
	public AjaxJson saveStatus(String appissueId, String status,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		AppissueEntity appissue = new AppissueEntity();
		appissue = systemService.getEntity(AppissueEntity.class,appissueId);
		appissue.setStatus(status);
		systemService.saveOrUpdate(appissue);
		if (status.equals("0")) {
			message = "已禁用";
		} else {
			message = "已发布";
		}
		j.setMsg(message);
		return j;
	}
}
