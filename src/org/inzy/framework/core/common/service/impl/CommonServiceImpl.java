package org.inzy.framework.core.common.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.inzy.framework.core.common.dao.ICommonDao;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.hibernate.qbc.HqlQuery;
import org.inzy.framework.core.common.hibernate.qbc.PageList;
import org.inzy.framework.core.common.model.common.DBTable;
import org.inzy.framework.core.common.model.common.UploadFile;
import org.inzy.framework.core.common.model.json.ComboTree;
import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.common.model.json.DataGridReturn;
import org.inzy.framework.core.common.model.json.ImportFile;
import org.inzy.framework.core.common.model.json.TreeGrid;
import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.core.tools.CommonUtils;
import org.inzy.framework.tag.vo.datatable.DataTableReturn;
import org.inzy.framework.tag.vo.easyui.Autocomplete;
import org.inzy.framework.tag.vo.easyui.ComboTreeModel;
import org.inzy.framework.tag.vo.easyui.TreeGridModel;
import org.inzy.framework.web.system.pojo.base.TSDepart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
	public ICommonDao commonDao = null;

	/**
	 * 获取所有数据库表
	 * 
	 * @return
	 */
	public List<DBTable> getAllDbTableName() {
		return commonDao.getAllDbTableName();
	}

	public Integer getAllDbTableSize() {
		return commonDao.getAllDbTableSize();
	}

	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}

	
	public <T> Serializable save(T entity) {
		return commonDao.save(entity);
	}

	
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);

	}

	
	public <T> void delete(T entity) {
		commonDao.delete(entity);

	}

	/**
	 * 删除实体集合
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities) {
		commonDao.deleteAllEntitie(entities);
	}

	/**
	 * 根据实体名获取对象
	 */
	public <T> T get(Class<T> class1, Serializable id) {
		return commonDao.get(class1, id);
	}

	/**
	 * 根据实体名返回全部对象
	 * 
	 * @param <T>
	 * @param hql
	 * @param size
	 * @return
	 */
	public <T> List<T> getList(Class clas) {
		return commonDao.loadAll(clas);
	}

	/**
	 * 根据实体名获取对象
	 */
	public <T> T getEntity(Class entityName, Serializable id) {
		return commonDao.getEntity(entityName, id);
	}

	/**
	 * 根据实体名称和字段名称和字段值获取唯一记录
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		return commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}

	/**
	 * 按属性查找对象列表.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value) {

		return commonDao.findByProperty(entityClass, propertyName, value);
	}

	/**
	 * 加载全部实体
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass) {
		return commonDao.loadAll(entityClass);
	}

	public <T> T singleResult(String hql) {
		return commonDao.singleResult(hql);
	}

	/**
	 * 删除实体主键ID删除对象
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		commonDao.deleteEntityById(entityName, id);
	}

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo) {
		commonDao.updateEntitie(pojo);

	}

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param hql
	 * @param objs
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql, Object... objs) {
		
		/** Modify by yun.tang 2015/07/07：扩展 为一个带数组参数的方法 */
		return commonDao.findByQueryString(hql, objs);
	}
	
	/**
	 * 通过hql 查询语句查找对象
	 *
	 * @param hql
	 * @param param
	 * @return
	 */
	public <T> List<T> findByQueryStringParam(String hql, Map<String, Object> param) {
		
		/** Add by yun.tang 2015/07/07：新增一个带Map参数的方法 */
		return commonDao.findByQueryStringParam(hql, param);
	}

	/**
	 * 根据sql更新
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql) {
		return commonDao.updateBySqlString(sql);
	}

	/**
	 * 根据sql查找List
	 * 
	 * @param query
	 * @parm objs
	 * @return
	 */
	public <T> List<T> findListbySql(String query, Object... objs) {
		
		/** Modify by yun.tang 2015/07/06：扩展 为一个带数组参数的方法 */
		return commonDao.findListbySql(query, objs);
	}

	/**
	 * 根据sql查找List
	 * 
	 * @param query
	 * @param param
	 * @return
	 */
	public <T> List<T> findListbySqlParam(String query,
			Map<String, Object> param) {
		
		/** Add by yun.tang 2015/07/06：新增一个带Map参数的方法 */
		return commonDao.findListbySqlParam(query, param);
	}

	/**
	 * 通过属性称获取实体带排序
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
		return commonDao.findByPropertyisOrder(entityClass, propertyName,
				value, isAsc);
	}

	/**
	 * 
	 * cq方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final CriteriaQuery cq, final boolean isOffset) {
		return commonDao.getPageList(cq, isOffset);
	}

	/**
	 * 返回DataTableReturn模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataTableReturn getDataTableReturn(final CriteriaQuery cq,
			final boolean isOffset) {
		return commonDao.getDataTableReturn(cq, isOffset);
	}

	/**
	 * 返回easyui datagrid模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(final CriteriaQuery cq,
			final boolean isOffset) {
		return commonDao.getDataGridReturn(cq, isOffset);
	}

	/**
	 * 
	 * hqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final HqlQuery hqlQuery,
			final boolean needParameter) {
		return commonDao.getPageList(hqlQuery, needParameter);
	}

	/**
	 * 
	 * sqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageListBySql(final HqlQuery hqlQuery,
			final boolean isToEntity) {
		return commonDao.getPageListBySql(hqlQuery, isToEntity);
	}

	public Session getSession()

	{
		return commonDao.getSession();
	}

	public List findByExample(final String entityName,
			final Object exampleEntity) {
		return commonDao.findByExample(entityName, exampleEntity);
	}

	/**
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
			Boolean ispage) {
		return commonDao.getListByCriteriaQuery(cq, ispage);
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 */
	public <T> T uploadFile(UploadFile uploadFile) {
		return commonDao.uploadFile(uploadFile);
	}

	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile)

	{
		return commonDao.viewOrDownloadFile(uploadFile);
	}

	/**
	 * 生成XML文件
	 * 
	 * @param fileName
	 *            XML全路径
	 * @return
	 */
	public HttpServletResponse createXml(ImportFile importFile) {
		return commonDao.createXml(importFile);
	}

	/**
	 * 解析XML文件
	 * 
	 * @param fileName
	 *            XML全路径
	 */
	public void parserXml(String fileName) {
		commonDao.parserXml(fileName);
	}

	public List<ComboTree> comTree(List<TSDepart> all, ComboTree comboTree) {
		return commonDao.comTree(all, comboTree);
	}

	public List<ComboTree> ComboTree(List all, ComboTreeModel comboTreeModel, List in, boolean recursive) {
        return commonDao.ComboTree(all, comboTreeModel, in, recursive);
	}

	/**
	 * 构建树形数据表
	 */
	public List<TreeGrid> treegrid(List all, TreeGridModel treeGridModel) {
		return commonDao.treegrid(all, treeGridModel);
	}

	/**
	 * 获取自动完成列表
	 * 
	 * @param <T>
	 * @return
	 */
	public <T> List<T> getAutoList(Autocomplete autocomplete) {
		StringBuffer sb = new StringBuffer("");
		for (String searchField : autocomplete.getSearchField().split(",")) {
			sb.append("  or " + searchField + " like '%"
					+ autocomplete.getTrem() + "%' ");
		}
		String hql = "from " + autocomplete.getEntityName() + " where 1!=1 "
				+ sb.toString();
		return commonDao.getSession().createQuery(hql)
				.setFirstResult(autocomplete.getCurPage() - 1)
				.setMaxResults(autocomplete.getMaxRows()).list();
	}

	/**
	 * 以jdbc方式查询 返回easyui datagrid模型
	 * 
	 * @param dataGrid
	 * @param sql
	 * @param objs
	 */
	public void findDataGridForJdbc(DataGrid dataGrid, String sql,
			Object... objs) {
		// 拼接查询总数
		String _sql = "select count(*) from (" + sql + " ) t";
		dataGrid.setTotal(this.getCountForJdbc(_sql, objs).intValue());
		dataGrid.setResults(this.findForJdbc(sql, dataGrid.getPage(),
				dataGrid.getRows(), objs));
	}

	/**
	 * 以jdbc方式查询 返回easyui datagrid模型
	 * 
	 * @param dataGrid
	 * @param sql
	 * @param param
	 */
	public void findDataGridForJdbcParam(DataGrid dataGrid, String sql,
			Map<String, Object> param) {
		
		// 拼接查询总数
		/** Add by yun.tang 2015/07/07：新增一个带Map参数的方法 */
		String _sql = "select count(*) from (" + sql + " ) t";
		dataGrid.setTotal(this.getCountForJdbcParam(_sql, param).intValue());
		dataGrid.setResults(this.findForJdbcParam(sql, dataGrid.getPage(),
				dataGrid.getRows(), param));
	}
	
	public Integer executeSql(String sql, List<Object> param) {
		return commonDao.executeSql(sql, param);
	}

	
	public Integer executeSql(String sql, Object... param) {
		return commonDao.executeSql(sql, param);
	}

	
	public Integer executeSql(String sql, Map<String, Object> param) {
		return commonDao.executeSql(sql, param);
	}
	
	public Object executeSqlReturnKey(String sql, Map<String, Object> param) {
		return commonDao.executeSqlReturnKey(sql, param);
	}
	
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
		return commonDao.findForJdbc(sql, page, rows);
	}
	
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return commonDao.findForJdbc(sql, objs);
	}
	
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Map<String, Object> param) {
		
		/** Modify by yun.tang 2015/06/26：改为一个带Map参数的方法 */
		return commonDao.findForJdbcParam(sql, page, rows, param);
	}
	
	/**
	 * 使用指定的检索标准检索数据并分页返回数据（带分页，条件参数 Object类型）
	 */
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz, Object... objs) {

		/** Modify by yun.tang 2015/06/04：扩展 为一个带数组参数的方法 */
		return commonDao.findObjForJdbc(sql, page, rows, clazz, objs);
	}

	/**
	 * 使用指定的检索标准检索数据并分页返回数据（带分页，条件参数 Map类型）
	 */
	public <T> List<T> findObjForJdbcParam(String sql, int page, int rows,
			Class<T> clazz, Map<String, Object> param) {

		/** Add by yun.tang 2015/06/04：扩展一个带Map参数的方法 */
		return commonDao.findObjForJdbcParam(sql, page, rows, clazz, param);
	}

	/**
	 * 使用指定的检索标准检索数据返回数据（不带分页，条件参数 Object类型）
	 */
	public <T> List<T> findObjForJdbc(String sql, Class<T> clazz,
			Object... objs) {

		/** Add by yun.tang 2015/06/12：扩展一个带数组参数的方法 */
		return commonDao.findObjForJdbc(sql, clazz, objs);
	}

	/**
	 * 使用指定的检索标准检索数据返回数据（不带分页，条件参数 Map类型）
	 */
	public <T> List<T> findObjForJdbcParam(String sql, Class<T> clazz,
			Map<String, Object> param) {

		/** Add by yun.tang 2015/06/12：扩展一个带Map参数的方法 */
		return commonDao.findObjForJdbcParam(sql, clazz, param);
	}
	
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		return commonDao.findOneForJdbc(sql, objs);
	}

	/**
	 * 使用指定的检索标准检索数据返回记录数 For JDBC 
	 */
	public Long getCountForJdbc(String sql, Object... objs) {
		
		/** Modify by yun.tang 2015/06/04：扩展为一个带数组参数的方法 */
		return commonDao.getCountForJdbc(sql, objs);
	}

	/**
	 * 使用指定的检索标准检索数据返回记录数 For JDBC-采用预处理方式 
	 */
	public Long getCountForJdbcParam(String sql, Map<String, Object> param) {
		
		/** Modify by yun.tang 2015/06/04：改为一个带Map参数的方法 */
		return commonDao.getCountForJdbcParam(sql, param);
	}
	
	public <T> void batchSave(List<T> entitys) {
		this.commonDao.batchSave(entitys);
	}

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findHql(String hql, Object... param) {
		return this.commonDao.findHql(hql, param);
	}

	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult) {
		return this.commonDao.pageList(dc, firstResult, maxResult);
	}

	public <T> List<T> findByDetached(DetachedCriteria dc) {
		return this.commonDao.findByDetached(dc);
	}
	
	/**
	 * 获得19位长整型主键值，格式：yyyyMMddhhmmssSSS+2位随机数
	 * 
	 * @return
	 */
	public long getLongKey() {
		return CommonUtils.getLongKey();
	}

}
