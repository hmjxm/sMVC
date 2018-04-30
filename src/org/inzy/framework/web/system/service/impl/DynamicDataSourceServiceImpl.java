package org.inzy.framework.web.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import org.inzy.framework.web.system.pojo.base.DynamicDataSourceEntity;
import org.inzy.framework.web.system.service.DynamicDataSourceServiceI;

@Service("dynamicDataSourceService")
@Transactional
public class DynamicDataSourceServiceImpl extends CommonServiceImpl implements DynamicDataSourceServiceI {
	
	/**初始化数据库信息，TOMCAT启动时直接加入到内存中**/
	public List<DynamicDataSourceEntity> initDynamicDataSource() {
		DynamicDataSourceEntity.DynamicDataSourceMap.clear();
		
		List<DynamicDataSourceEntity> dynamicSourceEntityList = this.commonDao.loadAll(DynamicDataSourceEntity.class);
		
		for (DynamicDataSourceEntity dynamicSourceEntity : dynamicSourceEntityList) {
			DynamicDataSourceEntity.DynamicDataSourceMap.put(dynamicSourceEntity.getDbKey(), dynamicSourceEntity);
		}
		return dynamicSourceEntityList;
	}
	
	public static DynamicDataSourceEntity getDbSourceEntityByKey(String dbKey) {
		DynamicDataSourceEntity dynamicDataSourceEntity = DynamicDataSourceEntity.DynamicDataSourceMap.get(dbKey);
		
		return dynamicDataSourceEntity;
	}
	
	public void refleshCache() {
		initDynamicDataSource();
	}
}