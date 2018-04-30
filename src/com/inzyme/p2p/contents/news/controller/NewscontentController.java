package com.inzyme.p2p.contents.news.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.service.SystemService;
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
import com.inzyme.p2p.contents.news.entity.NewscontentEntity;

/**
 * @Title: Controller
 * @Description: 新闻栏目
 * @author onlineGenerator
 * @date 2015-07-02 11:57:15
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/newscontentController")
public class NewscontentController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(NewscontentController.class);
	
	LinkedList<NewscontentEntity> files = new LinkedList<NewscontentEntity>();

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
	 * 新闻栏目列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "newscontent")
	public ModelAndView newscontent(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/p2p/contents/news/newscontentList");
	}

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(NewscontentEntity newscontent,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(NewscontentEntity.class, dataGrid);
		HqlGenerateUtil.installHql(cq, newscontent);
		String catelogId = request.getParameter("catelogId");
		if (catelogId != null && !catelogId.equals("")) {
			cq.eq("newscatelogEntity.id", catelogId);
		}else{
			cq.eq("id", " ");
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除新闻内容
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(NewscontentEntity newscontent,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		newscontent = systemService.getEntity(NewscontentEntity.class,
				newscontent.getId());
		try {
			systemService.delete(newscontent);
			message = "新闻删除成功";
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "新闻删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "saveStatus")
	@ResponseBody
	public AjaxJson saveStatus(String contentId, String status,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		NewscontentEntity newscontent = new NewscontentEntity();
		newscontent = systemService.getEntity(NewscontentEntity.class,
				contentId);
		newscontent.setStatus(status);
		systemService.saveOrUpdate(newscontent);
		if (status.equals("0")) {
			message = "已禁用";
		} else {
			message = "已发布";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 修改跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(NewscontentEntity newscontent,String catelogId,String catelogtitle,
			HttpServletRequest req,HttpServletResponse response) {
		//纯在中文乱码问题
		response.setCharacterEncoding("UTF-8");
		if (StringUtil.isNotEmpty(newscontent.getId())) {
			newscontent = systemService.getEntity(NewscontentEntity.class, newscontent.getId());
			req.setAttribute("newscontent", newscontent);
		}
		req.setAttribute("catelogId", catelogId);
		req.setAttribute("catelogtitle", catelogtitle);
		return new ModelAndView("com/inzyme/p2p/contents/news/newscontent");
	}

	
	/**
	 * 新闻保存
	 */                       
	@RequestMapping(params = "saveContent", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveContent(NewscontentEntity newscontent,HttpServletRequest request,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(newscontent.getId())) {
			message = "新闻: " + newscontent.getTitle() + "被更新成功";
			systemService.saveOrUpdate(newscontent);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			List<NewscontentEntity> contentList = systemService.findByProperty(NewscontentEntity.class, "title",newscontent.getTitle());
			if (contentList.size() > 0) {
				message = "新闻: " + newscontent.getTitle() + "已经存在";
			} else {
				message = "新闻: " + newscontent.getTitle() + "被添加成功";
				systemService.save(newscontent);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(message);
		return j;
	}
	

	
	@RequestMapping(params = "upload", method = RequestMethod.POST)
	@ResponseBody
	public LinkedList<NewscontentEntity> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());
			if (files.size() >= 10)
				files.pop();
			NewscontentEntity newscontent = new NewscontentEntity();
			try {
				
				String path ="upload/contents/";
				String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + path ;// 文件的硬盘真实路径
				String savePath = realPath + mpf.getOriginalFilename();// 文件保存全路径
				newscontent.setTitleimg(path+mpf.getOriginalFilename());
				FileCopyUtils.copy(mpf.getBytes(),new File(savePath));

			} catch (IOException e) {
				e.printStackTrace();
			}
			files.add(newscontent);

		}
		return files;
	}
	
}
