package com.inzyme.demo.imgupload.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.inzyme.demo.imgupload.entity.ImgUploadEntity;
import com.inzyme.demo.imgupload.service.ImgUploadServiceI;

/**
 * @Title: Controller
 * @Description: 上传下载示例
 * @author yun.tang
 * @date 2014-08-07 15:18:03
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/imgUploadController")
public class ImgUploadController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ImgUploadController.class);

	@Autowired
	private ImgUploadServiceI imgUploadService;
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
	 * 上传下载示例列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "imgUpload")
	public ModelAndView imgUpload(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/demo/imgupload/imgUploadList");
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
	public void datagrid(ImgUploadEntity imgUpload, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ImgUploadEntity.class, dataGrid);
		// 查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				imgUpload, request.getParameterMap());
		this.imgUploadService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除上传下载示例
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ImgUploadEntity imgUpload, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		imgUpload = systemService.getEntity(ImgUploadEntity.class,
				imgUpload.getId());
		message = "用户删除成功";
		imgUploadService.delete(imgUpload);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加上传下载示例
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ImgUploadEntity imgUpload, HttpServletRequest request) {

		AjaxJson j = new AjaxJson();
		imgUpload.setCreateTime(new Date());

		if (StringUtil.isNotEmpty(imgUpload.getId())) {
			message = "用户更新成功";
			ImgUploadEntity t = imgUploadService.get(ImgUploadEntity.class,
					imgUpload.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(imgUpload, t);
				imgUploadService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				logger.error("用户更新失败", e);
				message = "用户更新失败";
			}
		} else {
			message = "用户添加成功";
			imgUploadService.save(imgUpload);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setObj(imgUpload);
		j.setMsg(message);
		return j;
	}

	/**
	 * 上传下载示例列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ImgUploadEntity imgUpload,
			HttpServletRequest req) {

		// 功能分类：1-新增、2-修改、3-查看
		String functype = req.getParameter("functype");
		req.setAttribute("functype", functype);

		if (StringUtil.isNotEmpty(imgUpload.getId())) {
			imgUpload = imgUploadService.getEntity(ImgUploadEntity.class,
					imgUpload.getId());
			req.setAttribute("imgUploadPage", imgUpload);

			// 处理照片显示高和宽
			int iWidth = (imgUpload.getImgWidth() == null) ? 0 : imgUpload
					.getImgWidth();
			int iHeight = (imgUpload.getImgHeight() == null) ? 0 : imgUpload
					.getImgHeight();

			if (iWidth > 300) {
				iHeight = iHeight * 300 / iWidth;
				iWidth = 300;
			}
			req.setAttribute("iWidth", iWidth);
			req.setAttribute("iHeight", iHeight);
		}
		return new ModelAndView("com/inzyme/demo/imgupload/imgUpload");
	}

	/**
	 * 文件上传处理
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(params = "uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson uploadFile(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {

		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		// 读取or设置用户表数据
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		Integer iAge = (StringUtil.isEmpty(age)) ? null : Integer.parseInt(age);

		ImgUploadEntity iue = null;
		if (StringUtil.isEmpty(id)) {
			iue = new ImgUploadEntity();
		} else {
			iue = imgUploadService.getEntity(ImgUploadEntity.class, id);
		}
		iue.setUserName(userName);
		iue.setSex(sex);
		iue.setAge(iAge);

		// 处理文件上传
		String path = null;
		String filePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/upload/userImg";

		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs(); // 创建根目录
		}

		int imgWidth = 0;
		int imgHeight = 0;
		BufferedImage bufferedImage = null;
		File upFile = null;

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile tmpfile = entity.getValue();// 获取上传文件对象
			String filename = tmpfile.getOriginalFilename();
			upFile = new File(filePath + File.separator + filename);
			tmpfile.transferTo(upFile);
			path = "/upload/userImg/" + filename;

			// 取得图片宽和高
			bufferedImage = ImageIO.read(upFile);
			imgWidth = bufferedImage.getWidth();
			imgHeight = bufferedImage.getHeight();
			iue.setImgWidth(imgWidth);
			iue.setImgHeight(imgHeight);
		}

		iue.setImgUrl(path);
		iue.setCreateTime(new Date());

		// 新增
		if (StringUtil.isEmpty(id)) {
			imgUploadService.save(iue);
		} else
		// 修改
		{
			imgUploadService.saveOrUpdate(iue);
		}

		j.setMsg("文件上传成功并且数据更新成功！");

		return j;
	}

	@RequestMapping(params = "downloadNet", method = RequestMethod.GET)
	@ResponseBody
	public void downloadNet(String id, HttpServletRequest request,
			HttpServletResponse response) {

		ImgUploadEntity iue = systemService.get(ImgUploadEntity.class, id);
		if (iue == null) {
			return;
		}

		String path = iue.getImgUrl();
		String filePath = request.getSession().getServletContext()
				.getRealPath(path);
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}

		String filename = file.getName();
		logger.debug("path:\t" + path);
		logger.debug("filePath:\t" + filePath);
		logger.debug("filename:\t" + filename);

		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");

		try {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String((filename).getBytes("GBK"), "ISO8859-1"));
		} catch (UnsupportedEncodingException e1) {
			logger.error("文件名读取失败", e1);
		}
		InputStream fis = null;
		OutputStream toClient = null;
		try {
			fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);

			toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
		} catch (Exception e) {
			logger.error("文件下载失败", e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error("文件下载失败", e);
				}
			}
			if (toClient != null) {
				try {
					toClient.close();
				} catch (IOException e) {
					logger.error("文件下载失败", e);
				}
			}
		}
	}

	/**
	 * 图片预览页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "previewImg")
	public ModelAndView previewImg(HttpServletRequest request) {

		String filepath = request.getParameter("filepath");
		String imgWidth = request.getParameter("imgWidth");
		String imgHeight = request.getParameter("imgHeight");
		request.setAttribute("filepath", filepath);
		request.setAttribute("imgWidth", imgWidth);
		request.setAttribute("imgHeight", imgHeight);

		return new ModelAndView("com/inzyme/demo/imgupload/imgUploadPreview");
	}
}
