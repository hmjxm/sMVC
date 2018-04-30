package com.inzyme.p2p.rules.bizrule.controller;

import com.inzyme.p2p.rules.bizrule.entity.InteresttypeEntity;
import com.inzyme.p2p.rules.bizrule.entity.InvestbidtypeEntity;
import com.inzyme.p2p.rules.bizrule.service.InteresttypeServiceI;
import com.inzyme.p2p.rules.bizrule.page.InteresttypePage;
import com.inzyme.p2p.rules.bizrule.entity.InterestargEntity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.FileMeta;
import org.inzy.framework.web.demo.entity.test.InzyOrderCustomEntity;
import org.inzy.framework.web.demo.entity.test.InzyOrderMainEntity;
import org.inzy.framework.web.demo.entity.test.InzyOrderProductEntity;
import org.inzy.framework.web.demo.page.InzyOrderMainPage;
import org.inzy.framework.web.system.pojo.base.TSDepart;
import org.inzy.framework.web.system.service.SystemService;
import org.inzy.framework.core.util.MyBeanUtils;

/**
 * @Title: Controller
 * @Description: 计息种类
 * @author onlineGenerator
 * @date 2015-07-04 20:08:18
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/interesttypeController")
public class InteresttypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(InteresttypeController.class);

	@Autowired
	private InteresttypeServiceI interesttypeService;
	@Autowired
	private SystemService systemService;

	LinkedList<FileMeta> icon = new LinkedList<FileMeta>();
	FileMeta fileMeta = null;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 计息种类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "interesttype")
	public ModelAndView interesttype(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/p2p/rules/bizrule/interesttypeList");
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
	public void datagrid(InteresttypeEntity interesttype,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InteresttypeEntity.class, dataGrid);
		this.interesttypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除计息种类
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InteresttypeEntity interesttype,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		interesttype = systemService.getEntity(InteresttypeEntity.class,
				interesttype.getId());
		String message = "计息种类删除成功";
		interesttypeService.delMain(interesttype);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(InteresttypeEntity interesttype,
			InteresttypePage interesttypePage, HttpServletRequest request) {
		List<InterestargEntity> interestargList = interesttypePage
				.getInterestargList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(interesttype.getId())) {
			message = "更新成功";
			interesttypeService.updateMain(interesttype, interestargList);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			interesttypeService.addMain(interesttype, interestargList);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 订单信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InteresttypeEntity interesttype,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(interesttype.getId())) {
			interesttype = interesttypeService.getEntity(
					InteresttypeEntity.class, interesttype.getId());
			req.setAttribute("interesttypePage", interesttype);
		}
		return new ModelAndView("com/inzyme/p2p/rules/bizrule/interesttype");
	}

	/**
	 * 加载明细列表[计息种类参数]
	 * 
	 * @return
	 */

	@RequestMapping(params = "interestargList")
	public ModelAndView interestargList(InteresttypeEntity interesttype,
			HttpServletRequest req) {
		Object id0 = interesttype.getId();
		String hql0 = "from InterestargEntity where 1 = 1 AND iNTERESTTYPEID = ? ";
		try {
			List<InterestargEntity> interestargEntityList = systemService
					.findHql(hql0, id0);
			req.setAttribute("interestargList", interestargEntityList);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/inzyme/p2p/rules/bizrule/interestargList");
	}

	@RequestMapping(params = "updatestatus")
	@ResponseBody
	public AjaxJson updatestatus(InteresttypeEntity interesttype,
			HttpServletRequest request) {
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(interesttype.getId())) {
			InteresttypeEntity t = interesttypeService.get(
					InteresttypeEntity.class, id);
			try {
				if (flag.equals("1")) {
					message = "启用成功";
					t.setStatus("1");
				}
				if (flag.equals("2")) {
					message = "禁用成功";
					t.setStatus("0");
				}
				interesttypeService.saveOrUpdate(t);
				j.setMsg(message);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return j;
	}

	@RequestMapping(params = "upload", method = RequestMethod.POST)
	@ResponseBody
	public LinkedList<FileMeta> upload(MultipartHttpServletRequest request,
			HttpServletResponse response) {
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			try {
				String path = "upload/";
				fileMeta.setBytes(mpf.getBytes());
				String realPath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "/" + path;// 文件的硬盘真实路径
				String savePath = realPath + mpf.getOriginalFilename();// 文件保存全路径
				FileCopyUtils.copy(mpf.getBytes(), new File(savePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			icon.add(fileMeta);
		}
		return icon;
	}
}
