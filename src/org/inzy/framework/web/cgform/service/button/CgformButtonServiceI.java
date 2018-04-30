package org.inzy.framework.web.cgform.service.button;

import java.util.List;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.cgform.entity.button.CgformButtonEntity;


/**
 * 
 * @author  Goodman Zhang
 *
 */
public interface CgformButtonServiceI extends CommonService{
	
	/**
	 * 查询按钮list
	 * @param formId
	 * @return
	 */
	public List<CgformButtonEntity> getCgformButtonListByFormId(String formId);

	/**
	 * 校验按钮唯一性
	 * @param cgformButtonEntity
	 * @return
	 */
	public List<CgformButtonEntity> checkCgformButton(CgformButtonEntity cgformButtonEntity);
	
	
}
