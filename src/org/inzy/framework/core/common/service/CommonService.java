package org.inzy.framework.core.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
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
import org.inzy.framework.tag.vo.datatable.DataTableReturn;
import org.inzy.framework.tag.vo.easyui.Autocomplete;
import org.inzy.framework.tag.vo.easyui.ComboTreeModel;
import org.inzy.framework.tag.vo.easyui.TreeGridModel;
import org.inzy.framework.web.system.pojo.base.TSDepart;
import org.springframework.dao.DataAccessException;

public interface CommonService {
	/**
	 * 获取所有数据库表
	 * 
	 * @return
	 */
	public List<DBTable> getAllDbTableName();
	
	public Integer getAllDbTableSize();

	public <T> Serializable save(T entity);

	public <T> void saveOrUpdate(T entity);

	public <T> void delete(T entity);

	public <T> void batchSave(List<T> entitys);

	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> class1, Serializable id);

	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T getEntity(Class entityName, Serializable id);

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
			String propertyName, Object value);

	/**
	 * 按属性查找对象列表.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * 加载全部实体
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);

	/**
	 * 删除实体主键删除
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id);

	/**
	 * 删除实体集合
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities);

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo);

	/**
	 * 通过hql 查询语句查找对象
	 * Modify by yun.tang 2015/07/07：扩展 为一个带数组参数的方法
	 *
	 * @param hql
	 * @param objs
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql, Object... objs);
	
	/**
	 * 通过hql 查询语句查找对象
	 * Add by yun.tang 2015/07/07：新增一个带Map参数的方法
	 *
	 * @param hql
	 * @param param
	 * @return
	 */
	public <T> List<T> findByQueryStringParam(String hql, Map<String, Object> param);

	/**
	 * 根据sql更新
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql);

	/**
	 * 根据sql查找List
	 * Modify by yun.tang 2015/07/06：扩展 为一个带数组参数的方法
	 *
	 * @param query
	 * @parm objs
	 * @return
	 */
	public <T> List<T> findListbySql(String query, Object... objs);
	
	/**
	 * 根据sql查找List
	 * Add by yun.tang 2015/07/06：新增一个带Map参数的方法
	 * 
	 * @param query
	 * @param param
	 * @return
	 */
	public <T> List<T> findListbySqlParam(String query, Map<String, Object> param);

	/**
	 * 通过属性称获取实体带排序
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc);

	public <T> List<T> getList(Class clas);

	public <T> T singleResult(String hql);

	/**
	 * 
	 * cq方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final CriteriaQuery cq, final boolean isOffset);

	/**
	 * 返回DataTableReturn模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataTableReturn getDataTableReturn(final CriteriaQuery cq,
			final boolean isOffset);

	/**
	 * 返回easyui datagrid模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(final CriteriaQuery cq,
			final boolean isOffset);

	/**
	 * 
	 * hqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final HqlQuery hqlQuery,
			final boolean needParameter);

	/**
	 * 
	 * sqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageListBySql(final HqlQuery hqlQuery,
			final boolean isToEntity);

	public Session getSession();

	public List findByExample(final String entityName,
			final Object exampleEntity);

	/**
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
			Boolean ispage);

	/**
	 * 文件上传
	 * 
	 * @param request
	 */
	public <T> T uploadFile(UploadFile uploadFile);

	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);

	/**
	 * 生成XML文件
	 * 
	 * @param fileName
	 *            XML全路径
	 */
	public HttpServletResponse createXml(ImportFile importFile);

	/**
	 * 解析XML文件
	 * 
	 * @param fileName
	 *            XML全路径
	 */
	public void parserXml(String fileName);

	public List<ComboTree> comTree(List<TSDepart> all, ComboTree comboTree);

	/**
	 * 根据模型生成JSON
	 * 
	 * @param all 全部对象
	 * @param in 已拥有的对象
	 * @param recursive 是否递归加载所有子节点
     * @return List<ComboTree>
	 */
	public List<ComboTree> ComboTree(List all, ComboTreeModel comboTreeModel, List in, boolean recursive);


    /**
     * 构建树形数据表
	 * 
	 * @param all
	 * @param treeGridModel
	 * @return
	 */
	public List<TreeGrid> treegrid(List all, TreeGridModel treeGridModel);

	/**
	 * 获取自动完成列表
	 * 
	 * @param <T>
	 * @return
	 */
	public <T> List<T> getAutoList(Autocomplete autocomplete);

	/**
	 * 以jdbc方式查询 返回easyui datagrid模型
	 * 
	 * @param dataGrid
	 * @param sql
	 * @param objs
	 */
	public void findDataGridForJdbc(DataGrid dataGrid, String sql, Object... objs);
	
	/**
	 * 以jdbc方式查询 返回easyui datagrid模型
	 * Add by yun.tang 2015/07/07：新增一个带Map参数的方法
	 * 
	 * @param dataGrid
	 * @param sql
	 * @param param
	 */
	public void findDataGridForJdbcParam(DataGrid dataGrid, String sql, Map<String, Object> param);
	
	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, List<Object> param);

	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, Object... param);

	/**
	 * 执行SQL 使用:name占位符
	 */
	public Integer executeSql(String sql, Map<String, Object> param);
	/**
	 * 执行SQL 使用:name占位符,并返回执行后的主键值
	 */
	public Object executeSqlReturnKey(String sql, Map<String, Object> param);
	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public Map<String, Object> findOneForJdbc(String sql, Object... objs);

	/**
	 * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 * Modify by yun.tang 2015/06/26：扩展 为一个带数组参数的方法
	 */
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows);

	/**
	 * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 * Modify by yun.tang 2015/06/04：扩展 为一个带数组参数的方法
	 */
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz, Object... objs);
	
	/**
	 * 使用指定的检索标准检索数据并分页返回数据（带分页）
	 * Add by yun.tang 2015/06/04：扩展一个带Map参数的方法
	 */
	public <T> List<T> findObjForJdbcParam(String sql, int page, int rows,
			Class<T> clazz, Map<String, Object> param);
	
	/**
	 * 使用指定的检索标准检索数据返回数据（不带分页）
	 * Add by yun.tang 2015/06/12：扩展一个带数组参数的方法
	 */
	public <T> List<T> findObjForJdbc(String sql, Class<T> clazz, Object... objs);
	
	/**
	 * 使用指定的检索标准检索数据返回数据（不带分页）
	 * Add by yun.tang 2015/06/12：扩展一个带Map参数的方法
	 */
	public <T> List<T> findObjForJdbcParam(String sql, Class<T> clazz, 
			Map<String, Object> param);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据-采用预处理方式
	 *
	 * @param sql
	 * @param page
	 * @param rows
	 * @param param
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Map<String, Object> param);

	/**
	 * 使用指定的检索标准检索数据返回记录数 For JDBC
	 * Modify by yun.tang 2015/06/04：扩展为一个带数组参数的方法
	 */
	public Long getCountForJdbc(String sql, Object... objs);
	
	/**
	 * 使用指定的检索标准检索数据返回记录数 For JDBC-采用预处理方式
	 * Modify by yun.tang 2015/06/04：改为一个带Map参数的方法
	 */
	public Long getCountForJdbcParam(String sql, Map<String, Object> param);

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findHql(String hql, Object... param);

	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult);

	public <T> List<T> findByDetached(DetachedCriteria dc);
	
	/**
	 * 获得19位长整型主键值，格式：yyyyMMddhhmmssSSS+2位随机数
	 * 
	 * @return
	 */
	public long getLongKey();
}
