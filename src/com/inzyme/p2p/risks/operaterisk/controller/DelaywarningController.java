package com.inzyme.p2p.risks.operaterisk.controller;
import com.inzyme.p2p.risks.operaterisk.entity.DelaywarningEntity;
import com.inzyme.p2p.risks.operaterisk.service.DelaywarningServiceI;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @Description: 逾期预警
 * @author Auto-generator
 * @date 2015-07-15 17:58:13
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/delaywarningController")
public class DelaywarningController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DelaywarningController.class);

	@Autowired
	private DelaywarningServiceI delaywarningService;
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
	 * 逾期预警列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "delaywarning")
	public ModelAndView delaywarning(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/p2p/risks/operaterisk/delaywarningList");
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
	public void datagrid(DelaywarningEntity delaywarning,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DelaywarningEntity.class, dataGrid);
		//查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, delaywarning, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.delaywarningService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除逾期预警
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(DelaywarningEntity delaywarning, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		delaywarning = systemService.getEntity(DelaywarningEntity.class, delaywarning.getId());
		message = "逾期预警删除成功";
		try{
			delaywarningService.delete(delaywarning);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "逾期预警删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除逾期预警
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "逾期预警删除成功";
		try{
			for(String id:ids.split(",")){
				DelaywarningEntity delaywarning = systemService.getEntity(DelaywarningEntity.class, 
				id
				);
				delaywarningService.delete(delaywarning);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "逾期预警删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加逾期预警
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(DelaywarningEntity delaywarning, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "逾期预警添加成功";
		try{
			delaywarningService.save(delaywarning);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "逾期预警添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新逾期预警
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(DelaywarningEntity delaywarning, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "逾期预警更新成功";
		DelaywarningEntity t = delaywarningService.get(DelaywarningEntity.class, delaywarning.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(delaywarning, t);
			delaywarningService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "逾期预警更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 逾期预警新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(DelaywarningEntity delaywarning, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(delaywarning.getId())) {
			delaywarning = delaywarningService.getEntity(DelaywarningEntity.class, delaywarning.getId());
			req.setAttribute("delaywarningPage", delaywarning);
		}
		return new ModelAndView("com/inzyme/p2p/risks/operaterisk/delaywarning-add");
	}
	/**
	 * 逾期预警编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(DelaywarningEntity delaywarning, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(delaywarning.getId())) {
			delaywarning = delaywarningService.getEntity(DelaywarningEntity.class, delaywarning.getId());
			req.setAttribute("delaywarningPage", delaywarning);
		}
		return new ModelAndView("com/inzyme/p2p/risks/operaterisk/delaywarning-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/inzyme/p2p/risks/operaterisk/delaywarningUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DelaywarningEntity delaywarning,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(DelaywarningEntity.class, dataGrid);
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, delaywarning, request.getParameterMap());
		List<DelaywarningEntity> delaywarnings = this.delaywarningService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"逾期预警");
		modelMap.put(NormalExcelConstants.CLASS,DelaywarningEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("逾期预警列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,delaywarnings);
		return NormalExcelConstants.INZY_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(DelaywarningEntity delaywarning,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "逾期预警");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,DelaywarningEntity.class);
		modelMap.put(TemplateExcelConstants.LIST_DATA,null);
		return TemplateExcelConstants.INZY_TEMPLATE_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<DelaywarningEntity> listDelaywarningEntitys = ExcelImportUtil.importExcelByIs(file.getInputStream(),DelaywarningEntity.class,params);
				for (DelaywarningEntity delaywarning : listDelaywarningEntitys) {
					delaywarningService.save(delaywarning);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
}
