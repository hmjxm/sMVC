package org.inzy.framework.tag.core.easyui;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.inzy.framework.core.util.ApplicationContextUtil;
import org.inzy.framework.core.util.MutiLangUtil;
import org.inzy.framework.web.system.service.MutiLangServiceI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 类描述：列表删除操作项标签
 * 
 * @author Goodman Zhang
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class DataGridDelOptTag extends TagSupport {
	protected String url;
	protected String title;
	private String message;//询问链接的提示语
	private String exp;//判断链接是否显示的表达式
	private String funname;//自定义函数名称
	
	private String operationCode;//按钮的操作Code
	private String langArg;
	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	public int doEndTag() throws JspTagException {
		title = MutiLangUtil.doMutiLang(title, langArg);
		
		Tag t = findAncestorWithClass(this, DataGridTag.class);
		DataGridTag parent = (DataGridTag) t;
		parent.setDelUrl(url, title, message, exp, funname,operationCode);
		return EVAL_PAGE;
	}
	public void setFunname(String funname) {
		this.funname = funname;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	
	public void setLangArg(String langArg) {
		this.langArg = langArg;
	}
}
