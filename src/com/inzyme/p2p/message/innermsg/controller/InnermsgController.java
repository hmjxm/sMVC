package com.inzyme.p2p.message.innermsg.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inzyme.p2p.message.innermsg.entity.InnermsgEntity;
import com.inzyme.p2p.message.innermsg.service.InnermsgServiceI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil;
import org.inzy.framework.core.util.ResourceUtil;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.core.easyui.TagUtil;
import org.inzy.framework.web.system.pojo.base.TSUser;
import org.inzy.framework.web.system.service.SystemService;



/**   
 * @Title: Controller
 * @Description: 内部消息
 * @author Auto-generator
 * @date 2015-07-14 14:06:08
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/innermsgController")
public class InnermsgController extends BaseController {

	@Autowired
	private InnermsgServiceI innermsgService;
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
	 * 内部消息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "innermsg")
	public ModelAndView innermsg(HttpServletRequest request) {
		return new ModelAndView("com/inzyme/p2p/message/innermsg/innermsgList");
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
	public void datagrid(InnermsgEntity innermsg,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(InnermsgEntity.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, innermsg);
		String mailbox = request.getParameter("mailbox");
		if (mailbox != null && !mailbox.equals("")) {
			//查看个人邮件
			if(mailbox.equals("1")){
				TSUser u = ResourceUtil.getSessionUserName();
				cq.eq("reciever", u.getUserName());
			}
			cq.eq("mailbox", mailbox);
			
		}else{
			//String[] arr = {"0","1"};
			//cq.in("mailbox", arr);
			//查询空数据
			cq.eq("id", " ");
		}
		cq.add();
		this.innermsgService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除内部消息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(InnermsgEntity innermsg, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		innermsg = systemService.getEntity(InnermsgEntity.class, innermsg.getId());
		message = "内部消息删除成功";
		try{
			innermsgService.delete(innermsg);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "内部消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除内部消息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "内部消息删除成功";
		try{
			for(String id:ids.split(",")){
				InnermsgEntity innermsg = systemService.getEntity(InnermsgEntity.class, 
				id
				);
				innermsgService.delete(innermsg);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "内部消息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加内部消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(InnermsgEntity innermsg, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//时间默认当前时间
		//状态默认为未读0
		//发送人当前登录用户
		//邮件类型接收人为空则是公共邮箱0 否则为个人收件箱1
		//如果有多个接收人则插入多条记录
		innermsg.setSendtime(new Date());
		innermsg.setStatus("0");
		TSUser u = ResourceUtil.getSessionUserName();
		String userName = u.getUserName();
		innermsg.setSender(userName);
		String reciever = request.getParameter("userName");
		if(reciever == null || reciever.equals("")){
			//当收信人为空时
			innermsg.setReciever("");
			//公共邮件
			innermsg.setMailbox("0");
			systemService.save(innermsg);
		}else{
			//只有一个收信人
			if(reciever.indexOf(",") == -1){
				//公共邮件
				innermsg.setReciever(reciever);
				innermsg.setMailbox("1");
				systemService.save(innermsg);
			}else{
				//多个收信人插入多条数据
				List<InnermsgEntity> innerList = new ArrayList<InnermsgEntity>();
				String[] rec = reciever.split(",");
				InnermsgEntity inner = null;
				for (int i = 0; i < rec.length; i++) {
					inner = new InnermsgEntity();
					inner.setCreateUser(innermsg.getCreateUser());
					inner.setCreateTime(innermsg.getCreateTime());
					inner.setContent(innermsg.getContent());
					inner.setTitle(innermsg.getTitle());
					inner.setSendtime(new Date());
					inner.setStatus("0");
					inner.setSender(userName);
					inner.setReciever(rec[i]);
					inner.setMailbox("1");
					innerList.add(inner);
				}
				systemService.batchSave(innerList);
			}
		}
		
		message = "内部消息发送成功";
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 内部消息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(InnermsgEntity innermsg, HttpServletRequest req) {
		String sender = req.getParameter("sender");
		if (sender != null && !sender.equals("")) {
			req.setAttribute("sender", sender);
		}
		return new ModelAndView("com/inzyme/p2p/message/innermsg/innermsg");
	}
	
	/**
	 * 更新内部消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "changeStatus")
	@ResponseBody
	public void changeStatus(InnermsgEntity innermsg, HttpServletRequest request) {
		if (StringUtil.isNotEmpty(innermsg.getId())) {
			innermsg = innermsgService.getEntity(InnermsgEntity.class, innermsg.getId());
			//改变已读状态
			innermsg.setStatus("1");
			systemService.saveOrUpdate(innermsg);
		}
	}
	
	
	
	
	/**
	 * 内部消息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goDetail")
	public ModelAndView goDetail(InnermsgEntity innermsg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(innermsg.getId())) {
			innermsg = innermsgService.getEntity(InnermsgEntity.class, innermsg.getId());
			req.setAttribute("innermsg", innermsg);
		}
		return new ModelAndView("com/inzyme/p2p/message/innermsg/innermsg-detail");
	}
}
