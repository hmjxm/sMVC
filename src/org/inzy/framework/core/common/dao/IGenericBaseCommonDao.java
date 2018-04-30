package org.inzy.framework.core.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.inzy.framework.core.common.hibernate.qbc.CriteriaQuery;
import org.inzy.framework.core.common.hibernate.qbc.HqlQuery;
import org.inzy.framework.core.common.hibernate.qbc.PageList;
import org.inzy.framework.core.common.model.common.DBTable;
import org.inzy.framework.core.common.model.json.DataGridReturn;
import org.inzy.framework.tag.vo.datatable.DataTableReturn;

/**
 * 
 * 类描述：DAO层泛型基类接口
 * 
 * Goodman Zhang
 * @date： 日期：2012-12-8 时间：下午05:37:33
 * @version 1.0
 */
public interface IGenericBaseCommonDao {
	/**
	 * 获取所有数据库表
	 * 
	 * @return
	 */
	public List<DBTable> getAllDbTableName();

	public Integer getAllDbTableSize();

	public <T> Serializable save(T entity);

	public <T> void batchSave(List<T> entitys);

	public <T> void saveOrUpdate(T entity);

	/**
	 * 删除实体
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entitie
	 */
	public <T> void delete(T entitie);

	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityName, Serializable id);

	/**
	 * 根据实体名字获取唯一记录
	 * 
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
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T getEntity(Class entityName, Serializable id);

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

	public <T> void updateEntityById(Class entityName, Serializable id);

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
	 * 通过hql查询唯一对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> T singleResult(String hql);

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
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
			Boolean ispage);

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
			final boolean needParameter);

	public Session getSession();

	public List findByExample(final String entityName,
			final Object exampleEntity);

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public Map<Object, Object> getHashMapbyQuery(String query);

	/**
	 * 返回jquery datatables模型
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
	 * 执行SQL 使用:name占位符,并返回插入的主键值
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
	 */
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows);

	/**
	 * 通过JDBC查找对象集合，使用指定的检索标准检索数据并分页返回数据（带分页）
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
	 * 使用指定的检索标准检索数据返回数据(不分页)
	 * @return
	 */
	public <T> List<T> findObjForJdbc(String sql, 
			Class<T> clazz, Object... objs);
	
	/**
	 * 使用指定的检索标准检索数据返回数据(不分页)
	 * Add by yun.tang 2015/06/11：扩展一个带Map参数的方法
	 */
	public <T> List<T> findObjForJdbcParam(String sql, Class<T> clazz,
			Map<String, Object> param);
	
	/**
	 * 使用指定的检索标准检索数据并分页返回数据-采用预处理方式
	 * Modify by yun.tang 2015/06/26：改为一个带Map参数的方法
	 * 
	 * @param sql
	 * @param page
	 * @param rows
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Map<String, Object> param);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC
	 * Modify by yun.tang 2015/06/04：扩展为一个带数组参数的方法
	 */
	public Long getCountForJdbc(String sql, Object... objs);
	
	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
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

	/**
	 * 执行HQL语句操作更新
	 * 
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql);

	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult);

	public <T> List<T> findByDetached(DetachedCriteria dc);
}
