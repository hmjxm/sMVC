package org.inzy.framework.web.demo.controller.test;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.inzy.framework.web.demo.service.test.InzyBlobDataServiceI;
import org.inzy.framework.web.system.service.SystemService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller
 * @Description: Blob型数据操作例子
 * @author Quainty
 * @date 2013-06-07 14:46:08
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/inzyBlobDataController")
public class InzyBlobDataController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InzyBlobDataController.class);

	@Autowired
	private InzyBlobDataServiceI inzyBlobDataService;
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
	 * Blob型数据操作例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "inzyBlobData")
	public ModelAndView inzyBlobData(HttpServletRequest request) {
		return new ModelAndView("inzy/demo/test/inzyBlobDataList");
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
	public void datagrid(InzyBlobDataEntity inzyBlobData,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InzyBlobDataEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, inzyBlobData);
		this.inzyBlobDataService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Blob型数据操作例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(InzyBlobDataEntity inzyBlobData, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		inzyBlobData = systemService.getEntity(InzyBlobDataEntity.class, inzyBlobData.getId());
		message = "删除成功";
		inzyBlobDataService.delete(inzyBlobData);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params = "download")
	public void exportXls(HttpServletRequest request, String fileId, HttpServletResponse response) {
		// 从数据库取得数据
		InzyBlobDataEntity obj = systemService.getEntity(InzyBlobDataEntity.class, fileId);
	    try {      
	    	Blob attachment = obj.getAttachmentcontent();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String((obj.getAttachmenttitle()+"."+obj.getExtend()).getBytes("GBK"), "ISO8859-1"));
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
	@RequestMapping(params = "upload")
	@ResponseBody
    public AjaxJson upload(HttpServletRequest request, String documentTitle, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			try {
				inzyBlobDataService.saveObj(documentTitle, file);
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
			//break; // 不支持多个文件导入？
		}

		return j;
    }


	/**
	 * 添加Blob型数据操作例子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(InzyBlobDataEntity inzyBlobData, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(inzyBlobData.getId())) {
			message = "更新成功";
			InzyBlobDataEntity t = inzyBlobDataService.get(InzyBlobDataEntity.class, inzyBlobData.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(inzyBlobData, t);
				inzyBlobDataService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			inzyBlobDataService.save(inzyBlobData);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * Blob型数据操作例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(InzyBlobDataEntity inzyBlobData, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(inzyBlobData.getId())) {
			inzyBlobData = inzyBlobDataService.getEntity(InzyBlobDataEntity.class, inzyBlobData.getId());
			req.setAttribute("inzyBlobDataPage", inzyBlobData);
		}
		return new ModelAndView("inzy/demo/test/inzyBlobData");
	}
}
