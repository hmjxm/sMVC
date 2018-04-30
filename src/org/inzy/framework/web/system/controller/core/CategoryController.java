package org.inzy.framework.web.system.controller.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.inzy.framework.core.common.controller.BaseController;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.model.json.AjaxJson;
import org.inzy.framework.core.common.model.json.ComboTree;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.common.model.json.TreeGrid;
import org.inzy.framework.core.constant.Globals;
import org.inzy.framework.core.util.MutiLangUtil;
import org.inzy.framework.core.util.MyBeanUtils;
import org.inzy.framework.core.util.StringUtil;
import org.inzy.framework.tag.vo.easyui.ComboTreeModel;
import org.inzy.framework.tag.vo.easyui.TreeGridModel;
import org.inzy.framework.web.system.pojo.base.TSCategoryEntity;
import org.inzy.framework.web.system.pojo.base.TSIcon;
import org.inzy.framework.web.system.service.CategoryServiceI;
import org.inzy.framework.web.system.service.SystemService;

/**
 * @Title: Controller
 * @Description: 分类管理
 * @author JueYue
 * @date 2014-09-16 21:50:55
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/categoryController")
public class CategoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CategoryController.class);

	private static final String CATEGORY_LIST = "system/category/categoryList";
	private static final String CATEGORY_ADD_OR_UPDATE = "system/category/category";

	@Autowired
	private CategoryServiceI categoryService;

	@Autowired
	private SystemService systemService;

	/**
	 * 分类管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "category")
	public String category(HttpServletRequest request) {
		return CATEGORY_LIST;
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	@ResponseBody
	public List<TreeGrid> datagrid(TSCategoryEntity category,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSCategoryEntity.class, dataGrid);
		if (category.getId() == null || StringUtils.isEmpty(category.getId())) {
			cq.isNull("parent");
		} else {
			cq.eq("parent.code", category.getId());
			category.setId(null);
		}
		// 查询条件组装器
		org.inzy.framework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				category, request.getParameterMap());
		List<TSCategoryEntity> list = this.categoryService
				.getListByCriteriaQuery(cq, false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setIdField("code");
		treeGridModel.setSrc("id");
		treeGridModel.setTextField("name");
		treeGridModel.setIcon("icon_iconPath");
		treeGridModel.setParentText("parent_name");
		treeGridModel.setParentId("parent_code");
		treeGridModel.setChildList("list");
		treeGrids = systemService.treegrid(list, treeGridModel);
		return treeGrids;
	}

	/**
	 * 删除分类管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSCategoryEntity tSCategory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tSCategory = systemService.getEntity(TSCategoryEntity.class,
				tSCategory.getId());
		j.setMsg("分类管理删除成功");
		categoryService.delete(tSCategory);
		systemService.addLog(j.getMsg(), Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		return j;
	}

	/**
	 * 添加分类管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TSCategoryEntity category, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(category.getId())) {
			j.setMsg("分类管理更新成功");
			TSCategoryEntity t = categoryService.get(TSCategoryEntity.class,
					category.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(category, t);
				categoryService.saveOrUpdate(t);
				systemService.addLog(j.getMsg(), Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				logger.error(e.getMessage(), e.fillInStackTrace());
				j.setMsg("分类管理更新失败");
			}
		} else {
			j.setMsg("分类管理添加成功");
			categoryService.saveCategory(category);
			systemService.addLog(j.getMsg(), Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		return j;
	}

	/**
	 * 分类管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public String addorupdate(ModelMap map, TSCategoryEntity category) {
		if (StringUtil.isNotEmpty(category.getId())) {
			category = categoryService.findUniqueByProperty(TSCategoryEntity.class,
					"code",category.getId());
			map.put("categoryPage", category);
		}
		map.put("iconlist", systemService.findByProperty(TSIcon.class,
				"iconType", (short) 1));
		if (category.getParent() != null
				&& StringUtil.isNotEmpty(category.getParent().getId())) {
			TSCategoryEntity parent = categoryService.getEntity(
					TSCategoryEntity.class, category.getParent().getId());
			category.setParent(parent);
			map.put("categoryPage", category);
		}
		return CATEGORY_ADD_OR_UPDATE;
	}

	@RequestMapping(params = "combotree")
	@ResponseBody
	public List<ComboTree> combotree(String selfCode, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSCategoryEntity.class);
		if (StringUtils.isNotEmpty(comboTree.getId())) {
			cq.createAlias("parent", "parent");
			cq.eq("parent.code", comboTree.getId());
		} else if (StringUtils.isNotEmpty(selfCode)) {
			cq.eq("code", selfCode);
		} else {
			cq.isNull("parent");
		}
		cq.add();
		List<TSCategoryEntity> categoryList = systemService
				.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("code", "name", "list");
		comboTrees = systemService.ComboTree(categoryList, comboTreeModel,
				null, false);
		MutiLangUtil.setMutiTree(comboTrees);
		return comboTrees;
	}

	/**
	 * 鉴于树的问题,这里自己加载全部数据来做同步树
	 * 
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "tree")
	@ResponseBody
	public List<ComboTree> tree(String selfCode,ComboTree comboTree, boolean isNew) {
		CriteriaQuery cq = new CriteriaQuery(TSCategoryEntity.class);
		if (StringUtils.isNotEmpty(comboTree.getId())) {
			cq.createAlias("parent", "parent");
			cq.eq("parent.code", comboTree.getId());
		} else if (StringUtils.isNotEmpty(selfCode)) {
			cq.eq("code", selfCode);
		} else {
			cq.isNull("parent");
		}
		cq.add();
		List<TSCategoryEntity> categoryList = systemService
				.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (int i = 0; i < categoryList.size(); i++) {
			comboTrees.add(categoryConvertToTree(categoryList.get(i)));
		}
		return comboTrees;
	}

	private ComboTree categoryConvertToTree(TSCategoryEntity entity) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getCode());
		tree.setText(entity.getName());
		tree.setIconCls(entity.getIcon().getIconClas());
		if (entity.getList() != null && entity.getList().size() > 0) {
			List<ComboTree> comboTrees = new ArrayList<ComboTree>();
			for (int i = 0; i < entity.getList().size(); i++) {
				comboTrees.add(categoryConvertToTree(entity.getList().get(
						i)));
			}
			tree.setChildren(comboTrees);
		}
		return tree;
	}
}