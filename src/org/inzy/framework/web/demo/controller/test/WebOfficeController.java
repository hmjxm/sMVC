package org.inzy.framework.web.demo.controller.test;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.ExceptionUtil;
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.demo.entity.test.InzyBlobDataEntity;
import org.inzy.framework.web.demo.entity.test.WebOfficeEntity;
import org.inzy.framework.web.demo.service.test.WebOfficeServiceI;
import org.inzy.framework.web.system.pojo.base.TSDepart;
import org.inzy.framework.web.system.service.SystemService;


/**   
 * @Title: Controller
 * @Description: WebOffice例子
 * @author Goodman Zhang
 * @date 2013-07-08 10:54:19
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/webOfficeController")
public class WebOfficeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WebOfficeController.class);

	@Autowired
	private WebOfficeServiceI webOfficeService;
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
	 * WebOffice例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "webOffice")
	public ModelAndView webOffice(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/test/webOfficeList");
	}
	
	/**
	 * 方法描述:  官方示例
	 * 作    者： yiming.zhang
	 * 日    期： Nov 30, 2013-11:18:26 AM
	 * @param request
	 * @return 
	 * 返回类型： ModelAndView
	 */
	@RequestMapping(params = "webOfficeSample")
	public ModelAndView webOfficeSample(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/test/webOfficeSample");
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
	public void datagrid(WebOfficeEntity webOffice,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WebOfficeEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, webOffice, request.getParameterMap());
		this.webOfficeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除WebOffice例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(WebOfficeEntity webOffice, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		webOffice = systemService.getEntity(WebOfficeEntity.class, webOffice.getId());
		message = "删除成功";
		webOfficeService.delete(webOffice);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加WebOffice例子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(WebOfficeEntity webOffice, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(webOffice.getId())) {
			message = "更新成功";
			WebOfficeEntity t = webOfficeService.get(WebOfficeEntity.class, webOffice.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(webOffice, t);
				webOfficeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			webOfficeService.save(webOffice);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * WebOffice例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(WebOfficeEntity webOffice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(webOffice.getId())) {
			webOffice = webOfficeService.getEntity(WebOfficeEntity.class, webOffice.getId());
			req.setAttribute("webOfficePage", webOffice);
		}
		return new ModelAndView("inzy/demo/test/webOffice");
	}

	/**
	 * WebOffice例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "newDocument")
	public ModelAndView newDocument(WebOfficeEntity webOffice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(webOffice.getId())) {
			webOffice = webOfficeService.getEntity(WebOfficeEntity.class, webOffice.getId());
			req.setAttribute("docId", webOffice.getId());
			req.setAttribute("fileType", webOffice.getDoctype());
			req.setAttribute("docData", webOffice);
		} else {
			req.setAttribute("docId", "");
			req.setAttribute("fileType", req.getParameter("fileType"));
		}
		return new ModelAndView("inzy/demo/test/webOfficeEdit");
	}
//	@RequestMapping(params = "getWebOfficeView")
//	public ModelAndView getWebOfficeView(HttpServletRequest req) {
//		return new ModelAndView("inzy/demo/test/webOfficeView");
//	}
	
	@RequestMapping(params = "getDoc")
	public void getDoc(HttpServletRequest request, Integer fileId, HttpServletResponse response) {
		// 从数据库取得数据
		WebOfficeEntity obj = systemService.getEntity(WebOfficeEntity.class, fileId);
	    try {      
	    	Blob attachment = obj.getDoccontent();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String((obj.getDoctitle()+"."+obj.getDoctype()).getBytes("GBK"), "ISO8859-1"));
	        //从数据库中读取出来    , 输出给下载用
	        InputStream bis = attachment.getBinaryStream();      
	        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			long lTotalLen = 0;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
				lTotalLen += bytesRead;
			}
			response.setHeader("Content-Length", String.valueOf(lTotalLen));
			bos.flush();
			bos.close();
	    } catch (Exception  e){      
	        e.printStackTrace();      
	    }                 
	}
	@RequestMapping(params = "saveDoc")
	@ResponseBody
    public AjaxJson saveDoc(WebOfficeEntity webOffice, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			try {
				webOfficeService.saveObj(webOffice, file);
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
			//break; // 不支持多个文件导入？
		}

		return j;
    }
}
