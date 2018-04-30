package com.inzyme.p2p.advert.advert.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inzyme.p2p.advert.advert.entity.AdvertEntity;
import com.inzyme.p2p.advert.advert.service.AdvertServiceI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.inzy.framework.web.system.pojo.base.TSType;
import org.inzy.framework.web.system.pojo.base.TSTypegroup;
import org.inzy.framework.web.system.service.SystemService;
import org.inzy.framework.core.util.MyBeanUtils;


/**   
 * @Title: Controller
 * @Description: 广告表
 * @author Auto-generator
 * @date 2015-07-13 15:24:34
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/advertController")
public class AdvertController extends BaseController {
	/**
	 * Logger for this class
	 */
	@Autowired
	private AdvertServiceI advertService;
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
	 * 广告表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "advert")
	public ModelAndView Advert(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/p2p/advert/advert/advertList");
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
	public void datagrid(AdvertEntity Advert,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AdvertEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, Advert, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.advertService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除广告表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AdvertEntity Advert, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Advert = systemService.getEntity(AdvertEntity.class, Advert.getId());
		message = "广告表删除成功";
		try{
			advertService.delete(Advert);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "广告表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除广告表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "广告表删除成功";
		try{
			for(String id:ids.split(",")){
				AdvertEntity Advert = systemService.getEntity(AdvertEntity.class, 
				id
				);
				advertService.delete(Advert);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "广告表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 保存广告表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doSave")
	@ResponseBody
	public AjaxJson doSave(AdvertEntity advert, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(advert.getId())) {
			message = "广告: " + advert.getAdtname() + "更新成功";
			systemService.saveOrUpdate(advert);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			List<AdvertEntity> contentList = systemService.findByProperty(AdvertEntity.class, "adtname",advert.getAdtname());
			if (contentList.size() > 0) {
				message = "广告: " + advert.getAdtname() + "已经存在";
			} else {
				message = "广告: " + advert.getAdtname() + "添加成功";
				systemService.save(advert);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新广告表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(AdvertEntity Advert, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "广告表更新成功";
		AdvertEntity t = advertService.get(AdvertEntity.class, Advert.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(Advert, t);
			advertService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "广告表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 广告表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(AdvertEntity advert, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(advert.getId())) {
			advert = advertService.getEntity(AdvertEntity.class, advert.getId());
			req.setAttribute("advert", advert);
		}
		return new ModelAndView("com/inzyme/p2p/advert/advert/advert");
	}
	
	
	
	/**
	 * 获取下拉框类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "getType")
	@ResponseBody
	public AjaxJson getType(String typeCode, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		TSTypegroup tsg = systemService.findByProperty(TSTypegroup.class, "typegroupcode", typeCode).get(0);
		
		List<TSType> list = systemService.findByProperty(TSType.class, "TSTypegroup.id", tsg.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		for (TSType tsType : list) {
			map.put(tsType.getTypecode(), tsType.getTypename());
		}
		j.setAttributes(map);
		return j;
	}
	
	
	
	@RequestMapping(params = "saveStatus")
	@ResponseBody
	public AjaxJson saveStatus(String advertId, String status,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		AdvertEntity advert = new AdvertEntity();
		advert = systemService.getEntity(AdvertEntity.class,
				advertId);
		advert.setStatus(status);
		systemService.saveOrUpdate(advert);
		if (status.equals("0")) {
			message = "已禁用";
		} else {
			message = "已发布";
		}
		j.setMsg(message);
		return j;
	}

}
