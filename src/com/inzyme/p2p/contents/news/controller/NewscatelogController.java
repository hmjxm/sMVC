package com.inzyme.p2p.contents.news.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.exception.BusinessException;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.ComboTree;
import org.inzy.framework.core.common.model.json.TreeGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.vo.easyui.ComboTreeModel;
import org.inzy.framework.tag.vo.easyui.TreeGridModel;
import org.inzy.framework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.inzyme.p2p.contents.news.entity.NewscatelogEntity;

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
@RequestMapping("/newscatelogController")
public class NewscatelogController extends BaseController {

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
	@RequestMapping(params = "newscatelog")
	public ModelAndView newscatelog(HttpServletRequest request) {
		
		return new ModelAndView("com/inzyme/p2p/contents/news/newscatelogList");
	}

	@RequestMapping(params = "cateloggrid")
	@ResponseBody
	public Object cateloggrid(NewscatelogEntity newscatelog,HttpServletRequest request, HttpServletResponse response, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(NewscatelogEntity.class);
		if("yes".equals(request.getParameter("isSearch"))){
			treegrid.setId(null);
			newscatelog.setId(null);
		} 
		if(null != newscatelog.getTitle()){
			HqlGenerateUtil.installHql(cq, newscatelog);
		}
		if (treegrid.getId() != null) {
			cq.eq("newscatelogEntity.id", treegrid.getId());
		}
		if(treegrid.getId() == null){
			cq.isNull("newscatelogEntity");
		}
		cq.add();
		List<TreeGrid> catalogList =null;
		catalogList=systemService.getListByCriteriaQuery(cq, false);
		
		if(catalogList.size()==0 && newscatelog.getTitle() != null){ 
			cq = new CriteriaQuery(NewscatelogEntity.class);
			NewscatelogEntity catalog = new NewscatelogEntity();
			newscatelog.setNewscatelogEntity(catalog);
			HqlGenerateUtil.installHql(cq, catalog);
			catalogList =systemService.getListByCriteriaQuery(cq, false);
		}
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setTextField("title");
		treeGridModel.setParentText("title");
		treeGridModel.setParentId("id");
		treeGridModel.setSrc("description");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("NewscatelogEntitys");
        Map<String,Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("catelogcode", "catelogcode");
        fieldMap.put("catelogtype", "catelogtype");
        fieldMap.put("catelogurl", "catelogurl");
        fieldMap.put("abstracts", "abstracts");
        fieldMap.put("isopen", "isopen");
        treeGridModel.setFieldMap(fieldMap);
        treeGrids = systemService.treegrid(catalogList, treeGridModel);
        JSONArray jsonArray = new JSONArray();
        for (TreeGrid treeGrid : treeGrids) {
            jsonArray.add(JSON.parse(treeGrid.toJson()));
        }
        return jsonArray;
	}
	
	
	
	
	/**
	 * 栏目页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(NewscatelogEntity newscatelog, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(newscatelog.getId())) {
			newscatelog = systemService.getEntity(NewscatelogEntity.class, newscatelog.getId());
			req.setAttribute("newscatelog", newscatelog);
		}
		return new ModelAndView("com/inzyme/p2p/contents/news/newscatelog");
	}
	
	
	
	/**
	 * 新闻栏目保存
	 */                       
	@RequestMapping(params = "saveCatelog")
	@ResponseBody
	public AjaxJson saveCatelog(NewscatelogEntity newscatelog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		if (StringUtil.isNotEmpty(newscatelog.getId())) {
			message = "栏目: " + newscatelog.getTitle() + "被更新成功";
			systemService.saveOrUpdate(newscatelog);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			List<NewscatelogEntity> cataList = systemService.findByProperty(NewscatelogEntity.class, "catelogcode",newscatelog.getCatelogcode());
			if (cataList.size() > 0) {
				message = "栏目: " + newscatelog.getCatelogcode() + "已经存在";
			} else {
				message = "栏目: " + newscatelog.getCatelogcode() + "被添加成功";
				systemService.save(newscatelog);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(message);
		return j;
	}

	
	/**
	 * 父级权限列表
	 * 
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "catalogList")
	@ResponseBody
	public List<ComboTree> catalogList(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(NewscatelogEntity.class);
		if(null != request.getParameter("cataId")){
			cq.notEq("id", request.getParameter("cataId"));
		}
		if (comboTree.getId() != null) {
			cq.eq("newscatelogEntity.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("newscatelogEntity");
		}
		cq.add();
		List<NewscatelogEntity> catalogsList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "title", "NewscatalogEntitys");
		comboTrees = systemService.ComboTree(catalogsList, comboTreeModel, null, true);
		return comboTrees;
	}
	
	/**
	 * 启用新闻栏目
	 * 
	 * @return
	 */
	@RequestMapping(params = "saveIsOpen")
	@ResponseBody
	public AjaxJson saveIsOpen(String cateId,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		NewscatelogEntity newscatelog = new NewscatelogEntity();
		newscatelog = systemService.getEntity(NewscatelogEntity.class, cateId);
		
		String isopen = newscatelog.getIsopen();
		String newopen = "0";
		if(isopen.equals("0")){
			newopen = "1";
		}else{
			newopen = "0";
		}
		newscatelog.setIsopen(newopen);
		systemService.saveOrUpdate(newscatelog);
		if(isopen.equals("0")){
			message = "已禁用";
		}else{
			message = "已启用";
		}
		j.setMsg(message);
		return j;
	}
	
	@ResponseBody
	@RequestMapping(value="getCatelogTree",method = RequestMethod.POST )
	public List<ComboTree> getCatelogTree(){
		CriteriaQuery cq = new CriteriaQuery(NewscatelogEntity.class);
		cq.add();
		List<NewscatelogEntity> listTree = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> treeList = new ArrayList<ComboTree>();
		for (int i = 0; i < listTree.size(); i++) {
			if(listTree.get(i).getNewscatelogEntity()==null){
				treeList.add(categoryConvertToTree(listTree.get(i)));				
			}
		}		
		return treeList;
	}
	
	
	private ComboTree categoryConvertToTree(NewscatelogEntity newscatalogDTO) {
		ComboTree tree = new ComboTree();
		tree.setId(newscatalogDTO.getId());
		tree.setText(newscatalogDTO.getTitle());
		if (newscatalogDTO.getNewscatelogEntitys() != null && newscatalogDTO.getNewscatelogEntitys().size() > 0) {
			List<ComboTree> comboTrees = new ArrayList<ComboTree>();
			for (int i = 0; i < newscatalogDTO.getNewscatelogEntitys().size(); i++) {
				comboTrees.add(categoryConvertToTree(newscatalogDTO.getNewscatelogEntitys().get(
						i)));
			}
			tree.setChildren(comboTrees);
		}
		return tree;
	}
	
	
	/**
	 * 删除新闻栏目
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(NewscatelogEntity newscatalog,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		newscatalog = systemService.getEntity(NewscatelogEntity.class,
				newscatalog.getId());
		try {
			systemService.delete(newscatalog);
			message = "新闻栏目删除成功";
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "新闻栏目删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
}
