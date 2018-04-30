package org.inzy.framework.web.cgform.service.button;

import java.util.List;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.cgform.entity.button.CgformButtonEntity;
import org.inzy.framework.web.cgform.entity.button.CgformButtonSqlEntity;


public interface CgformButtonSqlServiceI extends CommonService{

	/**
	 * 校验按钮sql增强唯一性
	 * @param cgformButtonEntity
	 * @return
	 */
	public List<CgformButtonSqlEntity> checkCgformButtonSql(CgformButtonSqlEntity cgformButtonSqlEntity);
	
	/**
	 * 根据buttonCode和formId初始化数据
	 * @param buttonCode
	 * @param formId
	 * @return
	 */
	public CgformButtonSqlEntity getCgformButtonSqlByCodeFormId(String buttonCode,String formId);
}
