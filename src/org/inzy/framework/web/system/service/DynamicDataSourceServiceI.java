package org.inzy.framework.web.system.service;

import java.util.List;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.system.pojo.base.DynamicDataSourceEntity;

public interface DynamicDataSourceServiceI extends CommonService{
	
	public List<DynamicDataSourceEntity> initDynamicDataSource();
	
	public void refleshCache();
}
