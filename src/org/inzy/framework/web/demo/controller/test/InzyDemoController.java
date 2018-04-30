package org.inzy.framework.web.demo.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inzy.framework.core.annotation.config.AutoMenu;
import org.inzy.framework.core.annotation.config.AutoMenuOperation;
import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.CKEditorEntity;
import org.inzy.framework.web.demo.entity.test.InzyDemo;
import org.inzy.framework.web.demo.service.test.InzyDemoServiceI;
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
 * @Description: 单表模型（DEMO）
 * @author Goodman Zhang
 * @date 2013-01-23 17:12:40
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/inzyDemoController")
@AutoMenu(name = "常用Demo", url = "inzyDemoController.do?inzyDemo")
public class InzyDemoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InzyDemoController.class);

	@Autowired
	private InzyDemoServiceI inzyDemoService;
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
	 * popup 例子
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "popup")
	public ModelAndView popup(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/inzyDemo/popup");
	}
	/**
	 * popup 例子
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "selectUserList")
	public ModelAndView selectUserList(HttpServletRequest request) {
		String departsReplace = "";
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		for (TSDepart depart : departList) {
			if (departsReplace.length() > 0) {
				departsReplace += ",";
			}
			departsReplace += depart.getDepartname() + "_" + depart.getId();
		}
		request.setAttribute("departsReplace", departsReplace);
		return new ModelAndView("inzy/demo/inzyDemo/selectUserList");
	}
	
	/**
	 * ckeditor 例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "ckeditor")
	public ModelAndView ckeditor(HttpServletRequest request) {
//		CKEditorEntity t = inzyDemoService.get(CKEditorEntity.class, "1");
		CKEditorEntity t = inzyDemoService.loadAll(CKEditorEntity.class).get(0);
		request.setAttribute("cKEditorEntity", t);
		if(t.getContents() == null ){
			request.setAttribute("contents", "");
		}else {
			request.setAttribute("contents", new String (t.getContents()));
		}
		return new ModelAndView("inzy/demo/inzyDemo/ckeditor");
	}
	/**
	 * ckeditor saveCkeditor
	 * 
	 * @return
	 */
	@RequestMapping(params = "saveCkeditor")
	@ResponseBody
	public AjaxJson saveCkeditor(HttpServletRequest request,CKEditorEntity cKEditor , String contents) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(cKEditor.getId())) {
			CKEditorEntity t =inzyDemoService.get(CKEditorEntity.class, cKEditor.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(cKEditor, t);
				t.setContents(contents.getBytes());
				inzyDemoService.saveOrUpdate(t);
				j.setMsg("更新成功");
			} catch (Exception e) {
				e.printStackTrace();
				j.setMsg("更新失败");
			}
		} else {
			cKEditor.setContents(contents.getBytes());
			inzyDemoService.save(cKEditor);
		}
		return j;
	}

	/**
	 * InzyDemo 打印预览跳转
	 * @param inzyDemo
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "print")
	public ModelAndView print(InzyDemo inzyDemo, HttpServletRequest req) {
		// 获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);

		if (StringUtil.isNotEmpty(inzyDemo.getId())) {
			inzyDemo = inzyDemoService.getEntity(InzyDemo.class, inzyDemo
					.getId());
			req.setAttribute("jgDemo", inzyDemo);
			if ("0".equals(inzyDemo.getSex()))
				req.setAttribute("sex", "男");
			if ("1".equals(inzyDemo.getSex()))
				req.setAttribute("sex", "女");
		}
		return new ModelAndView("inzy/demo/inzyDemo/inzyDemoPrint");
	}

	/**
	 * InzyDemo例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "inzyDemo")
	public ModelAndView inzyDemo(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/inzyDemo/inzyDemoList");
	}

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(InzyDemo inzyDemo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InzyDemo.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, inzyDemo,request.getParameterMap());
		this.inzyDemoService.getDataGridReturn(cq, true);
		String total_salary = String.valueOf(inzyDemoService.findOneForJdbc("select sum(salary) as ssum from inzy_demo").get("ssum"));
		/*
		 * 说明：格式为 字段名:值(可选，不写该值时为分页数据的合计) 多个合计 以 , 分割
		 */
		dataGrid.setFooter("salary:"+(total_salary.equalsIgnoreCase("null")?"0.0":total_salary)+",age,email:合计");
		TagUtil.datagrid(response, dataGrid);
	}

	
	/**
	 * 权限列表
	 */
	@RequestMapping(params = "combox")
	@ResponseBody
	public List<InzyDemo> combox(HttpServletRequest request, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InzyDemo.class);
		List<InzyDemo> ls = this.inzyDemoService.getListByCriteriaQuery(cq, false);
		return ls;
	}
	/**
	 * 删除InzyDemo例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InzyDemo inzyDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		inzyDemo = systemService.getEntity(InzyDemo.class, inzyDemo.getId());
		message = "InzyDemo例子: " + inzyDemo.getUserName() + "被删除 成功";
		inzyDemoService.delete(inzyDemo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加InzyDemo例子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	@AutoMenuOperation(name="添加",code = "add")
	public AjaxJson save(InzyDemo inzyDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(inzyDemo.getId())) {
			message = "InzyDemo例子: " + inzyDemo.getUserName() + "被更新成功";
			InzyDemo t =inzyDemoService.get(InzyDemo.class, inzyDemo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(inzyDemo, t);
				inzyDemoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "InzyDemo例子: " + inzyDemo.getUserName() + "被添加成功";
			inzyDemo.setStatus("0");
			inzyDemoService.save(inzyDemo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}
	
	
	/**
	 * 审核报错
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAuthor")
	@ResponseBody
	public AjaxJson saveAuthor(InzyDemo inzyDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(inzyDemo.getId())) {
			message = "测试-用户申请成功";
			InzyDemo t =inzyDemoService.get(InzyDemo.class, inzyDemo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(inzyDemo, t);
				t.setStatus("1");
				inzyDemoService.saveOrUpdate(t);
				j.setMsg(message);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return j;
	}

	/**
	 * InzyDemo例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InzyDemo inzyDemo, HttpServletRequest req) {
		//获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		
		Map sexMap = new HashMap();
		sexMap.put(0, "男");
		sexMap.put(1, "女");
		req.setAttribute("sexMap", sexMap);
		
		if (StringUtil.isNotEmpty(inzyDemo.getId())) {
			inzyDemo = inzyDemoService.getEntity(InzyDemo.class, inzyDemo.getId());
			req.setAttribute("jgDemo", inzyDemo);
		}
		return new ModelAndView("inzy/demo/inzyDemo/inzyDemo");
	}

	/**
	 * InzyDemo例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdatemobile")
	public ModelAndView addorupdatemobile(InzyDemo inzyDemo, HttpServletRequest req) {
		//获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		
		Map sexMap = new HashMap();
		sexMap.put(0, "男");
		sexMap.put(1, "女");
		req.setAttribute("sexMap", sexMap);
		
		if (StringUtil.isNotEmpty(inzyDemo.getId())) {
			inzyDemo = inzyDemoService.getEntity(InzyDemo.class, inzyDemo.getId());
			req.setAttribute("jgDemo", inzyDemo);
		}
		return new ModelAndView("inzy/demo/inzyDemo/inzyDemomobile");
	}
	
	/**
	 * 设置签名跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doCheck")
	public ModelAndView doCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			InzyDemo inzyDemo = inzyDemoService.getEntity(InzyDemo.class,id);
			request.setAttribute("inzyDemo", inzyDemo);
		}
		return new ModelAndView("inzy/demo/inzyDemo/inzyDemo-check");
	}

	/**
	 * 全选删除Demo实例管理
	 * 
	 * @return
	 * @author tanghan
	 * @date 2013-07-13 14:53:00
	 */
	@RequestMapping(params = "doDeleteALLSelect")
	@ResponseBody
	public AjaxJson doDeleteALLSelect(InzyDemo inzyDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String ids = request.getParameter("ids");
		String[] entitys = ids.split(",");
	    List<InzyDemo> list = new ArrayList<InzyDemo>();
		for(int i=0;i<entitys.length;i++){
			inzyDemo = systemService.getEntity(InzyDemo.class, entitys[i]);
            list.add(inzyDemo);			
		}
		message = "删除成功";
		inzyDemoService.deleteAllEntitie(list);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
}
