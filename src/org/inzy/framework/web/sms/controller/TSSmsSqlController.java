package org.inzy.framework.web.sms.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.BrowserUtils;
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.ResourceUtil;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.poi.excel.ExcelExportUtil;
import org.inzy.framework.poi.excel.entity.ExportParams;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.sms.entity.TSSmsSqlEntity;
import org.inzy.framework.web.sms.service.TSSmsSqlServiceI;
import org.inzy.framework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



/**   
 * @Title: Controller
 * @Description: 业务SQL表
 * @author Auto-generator
 * @date 2014-09-17 23:52:58
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tSSmsSqlController")
public class TSSmsSqlController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TSSmsSqlController.class);

	@Autowired
	private TSSmsSqlServiceI tSSmsSqlService;
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
	 * 业务SQL表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tSSmsSql")
	public ModelAndView tSSmsSql(HttpServletRequest request) {
		return new ModelAndView("inzy/sms/tSSmsSqlList");
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
	public void datagrid(TSSmsSqlEntity tSSmsSql,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSSmsSqlEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsSql, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除业务SQL表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TSSmsSqlEntity tSSmsSql, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
		message = "业务SQL表删除成功";
		try{
			systemService.delete(tSSmsSql);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务SQL表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除业务SQL表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "业务SQL表删除成功";
		try{
			for(String id:ids.split(",")){
				TSSmsSqlEntity tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, 
				id
				);
				systemService.delete(tSSmsSql);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "业务SQL表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加业务SQL表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TSSmsSqlEntity tSSmsSql, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "业务SQL表添加成功";
		try{
			tSSmsSqlService.save(tSSmsSql);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务SQL表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新业务SQL表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TSSmsSqlEntity tSSmsSql, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "业务SQL表更新成功";
		TSSmsSqlEntity t = systemService.get(TSSmsSqlEntity.class, tSSmsSql.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tSSmsSql, t);
			systemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "业务SQL表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 业务SQL表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSSmsSqlEntity tSSmsSql, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSmsSql.getId())) {
			tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
			req.setAttribute("tSSmsSqlPage", tSSmsSql);
		}
		return new ModelAndView("inzy/sms/tSSmsSql-add");
	}
	/**
	 * 业务SQL表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSSmsSqlEntity tSSmsSql, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSmsSql.getId())) {
			tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
			req.setAttribute("tSSmsSqlPage", tSSmsSql);
		}
		return new ModelAndView("inzy/sms/tSSmsSql-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("inzy/sms/tSSmsSqlUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(TSSmsSqlEntity tSSmsSql,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "业务SQL表";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(TSSmsSqlEntity.class, dataGrid);
			org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsSql, request.getParameterMap());
			
			List<TSSmsSqlEntity> tSSmsSqls = this.systemService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExportParams("业务SQL表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), TSSmsSqlEntity.class, tSSmsSqls);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public void exportXlsByT(TSSmsSqlEntity tSSmsSql,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "业务SQL表";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			workbook = ExcelExportUtil.exportExcel(new ExportParams("业务SQL表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
					"导出信息"), TSSmsSqlEntity.class, null);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//			MultipartFile file = entity.getValue();// 获取上传文件对象
//			ImportParams params = new ImportParams();
//			params.setTitleRows(2);
//			params.setSecondTitleRows(1);
//			params.setNeedSave(true);
//			try {
//				List<TSSmsSqlEntity> listTSSmsSqlEntitys = 
//					(List<TSSmsSqlEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),TSSmsSqlEntity.class,params);
//				for (TSSmsSqlEntity tSSmsSql : listTSSmsSqlEntitys) {
//					systemService.save(tSSmsSql);
//				}
//				j.setMsg("文件导入成功！");
//			} catch (Exception e) {
//				j.setMsg("文件导入失败！");
//				logger.error(ExceptionUtil.getExceptionMessage(e));
//			}finally{
//				try {
//					file.getInputStream().close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return j;
	}
}