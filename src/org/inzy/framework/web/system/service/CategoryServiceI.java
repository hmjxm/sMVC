package org.inzy.framework.web.system.service;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.system.pojo.base.TSCategoryEntity;

public interface CategoryServiceI extends CommonService{
	/**
	 * 保存分类管理
	 * @param category
	 */
	void saveCategory(TSCategoryEntity category);

}
