package org.inzy.framework.web.demo.controller.test;
import java.util.List;

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
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.OptimisticLockingEntity;
import org.inzy.framework.web.demo.service.test.OptimisticLockingServiceI;
import org.inzy.framework.web.system.pojo.base.TSDepart;
import org.inzy.framework.web.system.service.SystemService;


/**   
 * @Title: Controller
 * @Description: 乐观锁测试
 * @author Goodman Zhang
 * @date 2013-06-24 14:46:42
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/optimisticLockingController")
public class OptimisticLockingController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OptimisticLockingController.class);

	@Autowired
	private OptimisticLockingServiceI optimisticLockingService;
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
	 * 乐观锁测试列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "optimisticLocking")
	public ModelAndView optimisticLocking(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/test/optimisticLockingList");
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
	public void datagrid(OptimisticLockingEntity optimisticLocking,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(OptimisticLockingEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, optimisticLocking, request.getParameterMap());
		this.optimisticLockingService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除乐观锁测试
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(OptimisticLockingEntity optimisticLocking, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		optimisticLocking = systemService.getEntity(OptimisticLockingEntity.class, optimisticLocking.getId());
		message = "删除成功";
		optimisticLockingService.delete(optimisticLocking);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加乐观锁测试
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(OptimisticLockingEntity optimisticLocking, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//optimisticLockingService.dd();
		if (StringUtil.isNotEmpty(optimisticLocking.getId())) {
			message = "更新成功";
			OptimisticLockingEntity t = optimisticLockingService.get(OptimisticLockingEntity.class, optimisticLocking.getId());
			try {
				org.inzy.framework.core.util.LogUtil.info("提交的版本号："+optimisticLocking.getVer()+"，当前版本号："+t.getVer());
				if(optimisticLocking.getVer()< t.getVer()){
					j.setSuccess(false);
					j.setMsg("提交的数据已过期");
					throw new Exception("提交的数据已过期");
				}
				MyBeanUtils.copyBeanNotNull2Bean(optimisticLocking, t);
				optimisticLockingService.updateEntitie(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			optimisticLockingService.save(optimisticLocking);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * 乐观锁测试列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(OptimisticLockingEntity optimisticLocking, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(optimisticLocking.getId())) {
			optimisticLocking = optimisticLockingService.getEntity(OptimisticLockingEntity.class, optimisticLocking.getId());
			req.setAttribute("optimisticLockingPage", optimisticLocking);
		}
		return new ModelAndView("inzy/demo/test/optimisticLocking");
	}
}
