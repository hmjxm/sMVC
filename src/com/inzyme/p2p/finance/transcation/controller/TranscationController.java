package com.inzyme.p2p.finance.transcation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil;
import org.inzy.framework.core.util.ExceptionUtil;
import org.inzy.framework.core.util.ResourceUtil;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.poi.excel.ExcelImportUtil;
import org.inzy.framework.poi.excel.entity.ExportParams;
import org.inzy.framework.poi.excel.entity.ImportParams;
import org.inzy.framework.poi.excel.entity.TemplateExportParams;
import org.inzy.framework.poi.excel.entity.vo.NormalExcelConstants;
import org.inzy.framework.poi.excel.entity.vo.TemplateExcelConstants;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.inzyme.p2p.finance.transcation.entity.TranscationEntity;
import com.inzyme.p2p.finance.transcation.service.TranscationServiceI;

/**
 * @Title: Controller
 * @Description: 账户交易记录
 * @author Auto-generator
 * @date 2015-07-16 10:04:50
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/transcationController")
public class TranscationController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(TranscationController.class);

	@Autowired
	private TranscationServiceI transcationService;
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
	 * 账户交易记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "transcation")
	public ModelAndView transcation(HttpServletRequest request,String accountId) {
		request.setAttribute("accountId", accountId);
		return new ModelAndView(
				"com/inzyme/p2p/finance/transcation/transcationList");
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
	public void datagrid(TranscationEntity transcation,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TranscationEntity.class, dataGrid);
		// 查询条件组装器
		HqlGenerateUtil.installHql(cq,transcation);
		try {
			// 自定义追加查询条件
			String accountId = request.getParameter("accountId");
			cq.eq("accountEntity.id", accountId);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.transcationService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 操作列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TranscationEntity transcation,
			HttpServletRequest req) {
		if (transcation.getId() != null) {
			transcation = systemService.getEntity(TranscationEntity.class,
					transcation.getId());
			req.setAttribute("tran", transcation);
		}
		String accountId = req.getParameter("accountId");
		req.setAttribute("accountId", accountId);
		String type = req.getParameter("type");
		req.setAttribute("type", type);
		return new ModelAndView("com/inzyme/p2p/finance/transcation/transcation");
	}

	/**
	 * 删除账户交易记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TranscationEntity transcation,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		transcation = systemService.getEntity(TranscationEntity.class,
				transcation.getId());
		message = "账户交易记录删除成功";
		try {
			transcationService.delete(transcation);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "账户交易记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除账户交易记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "账户交易记录删除成功";
		try {
			for (String id : ids.split(",")) {
				TranscationEntity transcation = systemService.getEntity(
						TranscationEntity.class, id);
				transcationService.delete(transcation);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "账户交易记录删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加账户交易记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TranscationEntity transcation,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(transcation.getId())) {
			message = "交易项目: " + transcation.getTitle() + "被更新成功";
			systemService.saveOrUpdate(transcation);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "交易项目: " + transcation.getTitle() + "被添加成功";
			systemService.save(transcation);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		//同步更新主表balance字段1：收入 2：支出
		String sql = "";
		Double sum = transcation.getAmount();
		//收入
		if(transcation.getDirection().equals("1")){
			sql = "update p2p_t_account set balance = balance + " + sum + " where id='"+transcation.getAccountEntity().getId()+"'";
		}else if(transcation.getDirection().equals("2")){
			//支出
			sql = "update p2p_t_account set balance = balance - " + sum + " where id='"+transcation.getAccountEntity().getId()+"'";
		}
		systemService.updateBySqlString(sql);
		return j;
	}


	/**
	 * 账户交易记录新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TranscationEntity transcation,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(transcation.getId())) {
			transcation = transcationService.getEntity(TranscationEntity.class,
					transcation.getId());
			req.setAttribute("transcationPage", transcation);
		}
		return new ModelAndView(
				"com/inzyme/p2p/finance/transcation/transcation-add");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView(
				"com/inzyme/p2p/finance/transcation/transcationUpload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TranscationEntity transcation,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TranscationEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, transcation);
		List<TranscationEntity> transcations = this.transcationService
				.getListByCriteriaQuery(cq, false);
		modelMap.put(NormalExcelConstants.FILE_NAME, "账户交易记录");
		modelMap.put(NormalExcelConstants.CLASS, TranscationEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("账户交易记录列表",
				"导出人:" + ResourceUtil.getSessionUserName().getRealName(),
				"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, transcations);
		return NormalExcelConstants.INZY_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TranscationEntity transcation,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "账户交易记录");
		modelMap.put(TemplateExcelConstants.PARAMS, new TemplateExportParams(
				"Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA, null);
		modelMap.put(TemplateExcelConstants.CLASS, TranscationEntity.class);
		modelMap.put(TemplateExcelConstants.LIST_DATA, null);
		return TemplateExcelConstants.INZY_TEMPLATE_EXCEL_VIEW;
	}

	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request,
			HttpServletResponse response) {
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
				List<TranscationEntity> listTranscationEntitys = ExcelImportUtil
						.importExcelByIs(file.getInputStream(),
								TranscationEntity.class, params);
				for (TranscationEntity transcation : listTranscationEntitys) {
					transcationService.save(transcation);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
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
