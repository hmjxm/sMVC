package org.inzy.framework.web.cgform.service.enhance;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.cgform.entity.enhance.CgformEnhanceJsEntity;


public interface CgformEnhanceJsServiceI extends CommonService{

	/**
	 * 根据cgJsType和formId查找数据
	 * @param cgJsType
	 * @param formId
	 * @return
	 */
	public CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(String cgJsType,String formId);
}
