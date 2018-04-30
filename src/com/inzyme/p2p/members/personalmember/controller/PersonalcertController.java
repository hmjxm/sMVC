package com.inzyme.p2p.members.personalmember.controller;

import com.inzyme.p2p.members.personalmember.entity.MemberEntity;
import com.inzyme.p2p.members.personalmember.entity.PersonalinfoEntity;
import com.inzyme.p2p.members.personalmember.entity.PersonalcertEntity;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import org.inzy.framework.core.util.oConvertUtils;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.pojo.base.TSDepart;
import org.inzy.framework.web.system.service.SystemService;
import org.inzy.framework.core.util.MyBeanUtils;

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

/**
 * @Title: Controller
 * @Description: 个人会员认证信息
 * @author onlineGenerator
 * @date 2015-07-02 15:59:13
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/personalcertController")
public class PersonalcertController extends BaseController {
	private SystemService systemService;

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
	 * 会员认证表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(PersonalinfoEntity personalinfo,
			HttpServletRequest request, DataGrid dataGrid) {
		String operid = oConvertUtils.getString(request.getParameter("operid"));
		List<PersonalcertEntity> list = new ArrayList<PersonalcertEntity>();
		list = systemService.findByProperty(PersonalcertEntity.class,
				"personalinfoid", operid);
		if (list.size() > 0) {
			PersonalcertEntity personalcert = list.get(0);
			request.setAttribute("personalcert", personalcert);
		}
		request.setAttribute("personalinfoid", operid);
		return new ModelAndView(
				"com/inzyme/p2p/members/personalmember/PersonalcertList");
	}

	/**
	 * 认证信息录入或修改
	 * 
	 * @param memberAuthentication
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HttpServletRequest req,
			PersonalcertEntity personalcert) {
		AjaxJson j = new AjaxJson();
		if (personalcert.getId().equals("")) {
			message = "认证信息表添加成功";
			try {
				systemService.save(personalcert);
				systemService.addLog(message, Globals.Log_Type_INSERT,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "认证信息表添加失败";
				throw new BusinessException(e.getMessage());
			}
		} else {
			PersonalcertEntity t = systemService.getEntity(
					PersonalcertEntity.class, personalcert.getId());
			try {
				message="认证信息表更新成功";
				MyBeanUtils.copyBeanNotNull2Bean(personalcert, t);
				systemService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "认证信息表更新失败";
			}
		}
		j.setMsg(message);
		return j;
	}
}
