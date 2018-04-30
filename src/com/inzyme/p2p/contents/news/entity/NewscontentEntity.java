package com.inzyme.p2p.contents.news.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 新闻内容
 * @author onlineGenerator
 * @date 2015-07-02 11:57:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_newscontent", schema = "")
@SuppressWarnings("serial")
public class NewscontentEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**创建人名称*/
	private String createName;
	/**创建人登录名称*/
	private String createBy;
	/**创建日期*/
	private Date createDate;
	/**更新人名称*/
	private String updateName;
	/**更新人登录名称*/
	private String updateBy;
	/**更新日期*/
	private Date updateDate;
	/**标题*/
	private String title;
	/**副标题*/
	private String subtitle;
	/**摘要*/
	private String abstracts;
	/**关键字*/
	private String keyword;
	/**内容*/
	private String content;
	/**是否存在标题图片*/
	private String hastitleimg;
	/**标题图片*/
	private String titleimg;
	/**宽度*/
	private String titleimgwidth;
	/**高度*/
	private String titleimgheight;
	/**作者*/
	private String author;
	/**新闻来源*/
	private String newsfrom;
	/**状态*/
	private String status;
	/**发布日期*/
	private Date publishdate;
	/**顶级*/
	private String topleve;
	/**序号*/
	private String orderno;
	
	private NewscatelogEntity newscatelogEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catelogid")
	public NewscatelogEntity getNewscatelogEntity() {
		return newscatelogEntity;
	}
	public void setNewscatelogEntity(NewscatelogEntity newscatelogEntity) {
		this.newscatelogEntity = newscatelogEntity;
	}

	/**
	 *方法: 取得String
	 *@return: String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置String
	 *@param: String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得String
	 *@return: String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人名称
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得String
	 *@return: String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置String
	 *@param: String  创建人登录名称
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得Date
	 *@return: Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置String
	 *@param: String  更新人名称
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得String
	 *@return: String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置String
	 *@param: String  更新人登录名称
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得Date
	 *@return: Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  更新日期
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得String
	 *@return: String  标题
	 */
	@Column(name ="TITLE",nullable=true,length=50)
	public String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置String
	 *@param: String  标题
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 *方法: 取得String
	 *@return: String  副标题
	 */
	@Column(name ="SUBTITLE",nullable=true,length=50)
	public String getSubtitle(){
		return this.subtitle;
	}

	/**
	 *方法: 设置String
	 *@param: String  副标题
	 */
	public void setSubtitle(String subtitle){
		this.subtitle = subtitle;
	}
	/**
	 *方法: 取得String
	 *@return: String  摘要
	 */
	@Column(name ="ABSTRACTS",nullable=true,length=1000)
	public String getAbstracts(){
		return this.abstracts;
	}

	/**
	 *方法: 设置String
	 *@param: String  摘要
	 */
	public void setAbstracts(String abstracts){
		this.abstracts = abstracts;
	}
	/**
	 *方法: 取得String
	 *@return: String  关键字
	 */
	@Column(name ="KEYWORD",nullable=true,length=50)
	public String getKeyword(){
		return this.keyword;
	}

	/**
	 *方法: 设置String
	 *@param: String  关键字
	 */
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	/**
	 *方法: 取得String
	 *@return: String  内容
	 */
	@Column(name ="CONTENT",nullable=true,length=2000)
	public String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置String
	 *@param: String  内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	/**
	 *方法: 取得String
	 *@return: String  是否存在标题图片
	 */
	@Column(name ="HASTITLEIMG",nullable=true,length=50)
	public String getHastitleimg(){
		return this.hastitleimg;
	}

	/**
	 *方法: 设置String
	 *@param: String  是否存在标题图片
	 */
	public void setHastitleimg(String hastitleimg){
		this.hastitleimg = hastitleimg;
	}
	/**
	 *方法: 取得String
	 *@return: String  标题图片
	 */
	@Column(name ="TITLEIMG",nullable=true,length=50)
	public String getTitleimg(){
		return this.titleimg;
	}

	/**
	 *方法: 设置String
	 *@param: String  标题图片
	 */
	public void setTitleimg(String titleimg){
		this.titleimg = titleimg;
	}
	/**
	 *方法: 取得String
	 *@return: String  宽度
	 */
	@Column(name ="TITLEIMGWIDTH",nullable=true,length=50)
	public String getTitleimgwidth(){
		return this.titleimgwidth;
	}

	/**
	 *方法: 设置String
	 *@param: String  宽度
	 */
	public void setTitleimgwidth(String titleimgwidth){
		this.titleimgwidth = titleimgwidth;
	}
	/**
	 *方法: 取得String
	 *@return: String  高度
	 */
	@Column(name ="TITLEIMGHEIGHT",nullable=true,length=50)
	public String getTitleimgheight(){
		return this.titleimgheight;
	}

	/**
	 *方法: 设置String
	 *@param: String  高度
	 */
	public void setTitleimgheight(String titleimgheight){
		this.titleimgheight = titleimgheight;
	}
	/**
	 *方法: 取得String
	 *@return: String  作者
	 */
	@Column(name ="AUTHOR",nullable=true,length=50)
	public String getAuthor(){
		return this.author;
	}

	/**
	 *方法: 设置String
	 *@param: String  作者
	 */
	public void setAuthor(String author){
		this.author = author;
	}
	/**
	 *方法: 取得String
	 *@return: String  新闻来源
	 */
	@Column(name ="NEWSFROM",nullable=true,length=50)
	public String getNewsfrom(){
		return this.newsfrom;
	}

	/**
	 *方法: 设置String
	 *@param: String  新闻来源
	 */
	public void setNewsfrom(String newsfrom){
		this.newsfrom = newsfrom;
	}
	/**
	 *方法: 取得String
	 *@return: String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=10)
	public String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置String
	 *@param: String  状态
	 */
	public void setStatus(String status){
		this.status = status;
	}
	/**
	 *方法: 取得Date
	 *@return: Date  发布日期
	 */
	@Column(name ="PUBLISHDATE",nullable=true,length=20)
	public Date getPublishdate(){
		return this.publishdate;
	}

	/**
	 *方法: 设置Date
	 *@param: Date  发布日期
	 */
	public void setPublishdate(Date publishdate){
		this.publishdate = publishdate;
	}
	/**
	 *方法: 取得String
	 *@return: String  顶级
	 */
	@Column(name ="TOPLEVE",nullable=true,length=10)
	public String getTopleve(){
		return this.topleve;
	}

	/**
	 *方法: 设置String
	 *@param: String  顶级
	 */
	public void setTopleve(String topleve){
		this.topleve = topleve;
	}
	/**
	 *方法: 取得String
	 *@return: String  序号
	 */
	@Column(name ="ORDERNO",nullable=true,length=10)
	public String getOrderno(){
		return this.orderno;
	}

	/**
	 *方法: 设置String
	 *@param: String  序号
	 */
	public void setOrderno(String orderno){
		this.orderno = orderno;
	}
}
