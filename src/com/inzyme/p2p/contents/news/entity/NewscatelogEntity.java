package com.inzyme.p2p.contents.news.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 新闻栏目
 * @author onlineGenerator
 * @date 2015-07-02 11:57:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "p2p_t_newscatelog", schema = "")
@SuppressWarnings("serial")
public class NewscatelogEntity implements java.io.Serializable {
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
	/**栏目代码*/
	private String catelogcode;
	/**栏目类型*/
	private String catelogtype;
	/**栏目模版ID*/
	private String catelogtemplateid;
	/**栏目地址*/
	private String catelogurl;
	/**模版类容ID*/
	private String contenttemplateid;
	/**标题*/
	private String title;
	/**摘要*/
	private String abstracts;
	/**是否标题图片*/
	private String hastitleimg;
	/**标题图片*/
	private String titleimg;
	/**图片宽度*/
	private String titleimgwidth;
	/**图片高度*/
	private String titleimgheight;
	/**描述*/
	private String description;
	/**序号*/
	private String orderno;
	/**是否展开*/
	private String isopen;
	
	private NewscatelogEntity newscatelogEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentid")
	public NewscatelogEntity getNewscatelogEntity() {
		return newscatelogEntity;
	}

	public void setNewscatelogEntity(NewscatelogEntity newscatelogEntity) {
		this.newscatelogEntity = newscatelogEntity;
	}

	private List<NewscatelogEntity> NewscatelogEntitys = new ArrayList<NewscatelogEntity>();//下属栏目
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newscatelogEntity")
	public List<NewscatelogEntity> getNewscatelogEntitys() {
		return NewscatelogEntitys;
	}

	public void setNewscatelogEntitys(
			List<NewscatelogEntity> newscatelogEntitys) {
		NewscatelogEntitys = newscatelogEntitys;
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
	 *@return: String  栏目代码
	 */
	
	@Column(name ="CATELOGCODE",nullable=true,length=50)
	public String getCatelogcode(){
		return this.catelogcode;
	}

	/**
	 *方法: 设置String
	 *@param: String  栏目代码
	 */
	public void setCatelogcode(String catelogcode){
		this.catelogcode = catelogcode;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  栏目类型
	 */
	
	@Column(name ="CATELOGTYPE",nullable=true,length=50)
	public String getCatelogtype(){
		return this.catelogtype;
	}

	/**
	 *方法: 设置String
	 *@param: String  栏目类型
	 */
	public void setCatelogtype(String catelogtype){
		this.catelogtype = catelogtype;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  栏目模版ID
	 */
	
	@Column(name ="CATELOGTEMPLATEID",nullable=true,length=36)
	public String getCatelogtemplateid(){
		return this.catelogtemplateid;
	}

	/**
	 *方法: 设置String
	 *@param: String  栏目模版ID
	 */
	public void setCatelogtemplateid(String catelogtemplateid){
		this.catelogtemplateid = catelogtemplateid;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  栏目地址
	 */
	
	@Column(name ="CATELOGURL",nullable=true,length=100)
	public String getCatelogurl(){
		return this.catelogurl;
	}

	/**
	 *方法: 设置String
	 *@param: String  栏目地址
	 */
	public void setCatelogurl(String catelogurl){
		this.catelogurl = catelogurl;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  模版类容ID
	 */
	
	@Column(name ="CONTENTTEMPLATEID",nullable=true,length=36)
	public String getContenttemplateid(){
		return this.contenttemplateid;
	}

	/**
	 *方法: 设置String
	 *@param: String  模版类容ID
	 */
	public void setContenttemplateid(String contenttemplateid){
		this.contenttemplateid = contenttemplateid;
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
	 *@return: String  是否标题图片
	 */
	
	@Column(name ="HASTITLEIMG",nullable=true,length=50)
	public String getHastitleimg(){
		return this.hastitleimg;
	}

	/**
	 *方法: 设置String
	 *@param: String  是否标题图片
	 */
	public void setHastitleimg(String hastitleimg){
		this.hastitleimg = hastitleimg;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  标题图片
	 */
	
	@Column(name ="TITLEIMG",nullable=true,length=100)
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
	 *@return: String  图片宽度
	 */
	
	@Column(name ="TITLEIMGWIDTH",nullable=true,length=50)
	public String getTitleimgwidth(){
		return this.titleimgwidth;
	}

	/**
	 *方法: 设置String
	 *@param: String  图片宽度
	 */
	public void setTitleimgwidth(String titleimgwidth){
		this.titleimgwidth = titleimgwidth;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  图片高度
	 */
	
	@Column(name ="TITLEIMGHEIGHT",nullable=true,length=50)
	public String getTitleimgheight(){
		return this.titleimgheight;
	}

	/**
	 *方法: 设置String
	 *@param: String  图片高度
	 */
	public void setTitleimgheight(String titleimgheight){
		this.titleimgheight = titleimgheight;
	}
	
	/**
	 *方法: 取得String
	 *@return: String  描述
	 */
	
	@Column(name ="DESCRIPTION",nullable=true,length=1000)
	public String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置String
	 *@param: String  描述
	 */
	public void setDescription(String description){
		this.description = description;
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
	
	/**
	 *方法: 取得String
	 *@return: String  是否展开
	 */
	
	@Column(name ="ISOPEN",nullable=true,length=2)
	public String getIsopen(){
		return this.isopen;
	}

	/**
	 *方法: 设置String
	 *@param: String  是否展开
	 */
	public void setIsopen(String isopen){
		this.isopen = isopen;
	}
	
}
