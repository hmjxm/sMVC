package com.inzyme.p2p.members.personalmember.controller;

import com.inzyme.p2p.contents.news.entity.NewscontentEntity;
import com.inzyme.p2p.members.personalmember.entity.MemberEntity;
import com.inzyme.p2p.members.personalmember.entity.PersonalinfoEntity;
import com.inzyme.p2p.members.personalmember.entity.PersonalcertEntity;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
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
import org.inzy.framework.web.system.pojo.base.TSDepart;
import org.inzy.framework.web.system.service.SystemService;
import org.inzy.framework.core.util.MyBeanUtils;

import java.io.File;
import java.io.OutputStream;
import org.inzy.framework.core.util.BrowserUtils;
import org.inzy.framework.poi.excel.ExcelExportUtil;
import org.inzy.framework.poi.excel.ExcelImportUtil;
import org.inzy.framework.poi.excel.entity.ExportParams;
import org.inzy.framework.poi.excel.entity.ImportParams;
import org.inzy.framework.poi.excel.entity.TemplateExportParams;
import org.inzy.framework.poi.excel.entity.vo.NormalExcelConstants;
import org.inzy.framework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.inzy.framework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import org.inzy.framework.core.util.ExceptionUtil;
import org.inzy.framework.web.system.pojo.base.TSTerritory;

/**
 * @Title: Controller
 * @Description: 个人会员基本信息
 * @author onlineGenerator
 * @date 2015-07-02 15:59:13
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/personalinfoController")
public class PersonalinfoController extends BaseController {
	private SystemService systemService;
	LinkedList<PersonalinfoEntity> files = new LinkedList<PersonalinfoEntity>();
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * 会员表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "PersonalinfoList")
	public ModelAndView PersonalinfoList(HttpServletRequest request) {
		return new ModelAndView(
				"com/inzyme/p2p/members/personalmember/PersonalinfoList");
	}

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(PersonalinfoEntity personalinfo,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PersonalinfoEntity.class, dataGrid);
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				personalinfo);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 批量删除会员表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "会员表删除成功";
		try {
			for (String id : ids.split(",")) {
				PersonalinfoEntity personalinfo = systemService.getEntity(
						PersonalinfoEntity.class, id);
				systemService
						.updateBySqlString("delete from p2p_t_personalcert where personalinfoid='"
								+ id + "'");
				systemService.delete(personalinfo);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "会员表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 会员表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HttpServletRequest req) {
		return new ModelAndView(
				"com/inzyme/p2p/members/personalmember/Personalinfo-add");
	}

	/**
	 * 会员表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(String id, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(id)) {
			PersonalinfoEntity personalinfo = systemService.getEntity(
					PersonalinfoEntity.class, id);
			req.setAttribute("personalinfo", personalinfo);
		}
		return new ModelAndView(
				"com/inzyme/p2p/members/personalmember/Personalinfo-update");
	}

	/**
	 * 添加会员表
	 * 
	 * @param personalinfoAdd
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PersonalinfoEntity personalinfo,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "会员表添加成功";
		try {
			systemService.save(personalinfo);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "会员表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新会员表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(PersonalinfoEntity personalinfo,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "会员表更新成功";
		PersonalinfoEntity t = systemService.getEntity(
				PersonalinfoEntity.class, personalinfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(personalinfo, t);
			String isthirdlogin = personalinfo.getIsthirdlogin();
			if (isthirdlogin.equals("no")) {
				t.setThirdloginname("");
				t.setThirdloginsite("");
			}
			systemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "会员表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 状态冻结与回复
	 * 
	 * @return
	 */
	@RequestMapping(params = "setstatus")
	@ResponseBody
	public AjaxJson setstatus(String operid, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		PersonalinfoEntity personalinfo = new PersonalinfoEntity();
		personalinfo = systemService
				.getEntity(PersonalinfoEntity.class, operid);
		String status = personalinfo.getStatus();
		if (status.equals("冻结")) {
			status = "正常";
		} else if (status.equals("正常")) {
			status = "冻结";
		}
		personalinfo.setStatus(status);
		systemService.saveOrUpdate(personalinfo);
		if (status.equals("正常")) {
			message = "已回复";
		} else {
			message = "已冻结";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 注销状态
	 * 
	 * @return
	 */
	@RequestMapping(params = "cancelstatus")
	@ResponseBody
	public AjaxJson cancelstatus(String operid, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		PersonalinfoEntity personalinfo = new PersonalinfoEntity();
		personalinfo = systemService
				.getEntity(PersonalinfoEntity.class, operid);
		String status = personalinfo.getStatus();
		if (!status.equals("注销")) {
			status = "注销";
			personalinfo.setStatus(status);
			systemService.saveOrUpdate(personalinfo);
			message = "已注销";
		} 
		else{
			message = "已经是注销状态";
		} 
		j.setMsg(message);
		return j;
	}
	/**
	 * 用户名是否存在
	 * 
	 * @return
	 */
	@RequestMapping(params = "isexist")
	@ResponseBody
	public AjaxJson isexist(String loginname, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		List<PersonalinfoEntity> list=new ArrayList<PersonalinfoEntity>();
		list = systemService.findByProperty(PersonalinfoEntity.class, "loginname",
				loginname);
		if(list.size()>0){
			message="用户名已存在";
		}
		else{
			message="用户名可用";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	@RequestMapping(params = "resetpwd")
	@ResponseBody
	public AjaxJson resetpwd(String operid, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		PersonalinfoEntity personalinfo = new PersonalinfoEntity();
		personalinfo = systemService
				.getEntity(PersonalinfoEntity.class, operid);
		personalinfo.setPassword("123456");
		systemService.saveOrUpdate(personalinfo);
		message = "密码已重置";
		j.setMsg(message);
		return j;
	}

	/**
	 * 获取省级地域
	 * 
	 * @return
	 */
	@RequestMapping(params = "getPTerritory")
	@ResponseBody
	public AjaxJson getPTerritory(short territorylevel,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		List<TSTerritory> list = new ArrayList<TSTerritory>();
		TSTerritory territory = new TSTerritory();
		list = systemService.findByProperty(TSTerritory.class,
				"territoryLevel", territorylevel);
		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject json = new JSONObject();
			json.put("territorycode", list.get(i).getTerritoryCode());
			json.put("territoryname", list.get(i).getTerritoryName());
			jsonarray.add(json);
		}
		j.setObj(jsonarray);
		return j;
	}

	/**
	 * 获取市级地域
	 * 
	 * @return
	 */
	@RequestMapping(params = "getCTerritory")
	@ResponseBody
	public AjaxJson getCTerritory(String territorycode,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		territorycode = territorycode.substring(1);
		List<TSTerritory> list = new ArrayList<TSTerritory>();
		list = systemService.findByProperty(TSTerritory.class, "territoryCode",
				territorycode);
		if (list.size() == 1) {
			String id = list.get(0).getId();
			list = systemService.findByProperty(TSTerritory.class,
					"TSTerritory.id", id);
			JSONArray jsonarray = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONObject json = new JSONObject();
				json.put("territorycode", list.get(i).getTerritoryCode());
				json.put("territoryname", list.get(i).getTerritoryName());
				jsonarray.add(json);
			}
			j.setObj(jsonarray);
			/*
			 * for(int i=0;i<jsonarray.size();i++){ JSONObject
			 * json=jsonarray.getJSONObject(i); String
			 * oper=json.getString("territoryname"); String
			 * tid=json.getString("territorycode"); System.out.println(oper);
			 * System.out.println(tid); }
			 */
		}
		return j;
	}

	/**
	 * 图片上传
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload", method = RequestMethod.POST)
	@ResponseBody
	public LinkedList<PersonalinfoEntity> upload(
			MultipartHttpServletRequest request, HttpServletResponse response) {
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			if (files.size() >= 10)
				files.pop();
			PersonalinfoEntity personalinfo = new PersonalinfoEntity();
			try {
				String path = "upload/members/personalmember/";
				String realPath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "/" + path;// 文件的硬盘真实路径
				File file = new File(realPath);
				if (!file.exists()) {
					file.mkdirs(); // 创建根目录
				}
				String savePath = realPath + mpf.getOriginalFilename();// 文件保存全路径
				personalinfo.setHeadimg(path + mpf.getOriginalFilename());
				FileCopyUtils.copy(mpf.getBytes(), new File(savePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			files.add(personalinfo);

		}
		return files;
	}
}
