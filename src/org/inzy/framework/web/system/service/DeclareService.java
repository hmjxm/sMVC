package org.inzy.framework.web.system.service;

import java.util.List;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.system.pojo.base.TSAttachment;


/**
 * 
 * @author  Goodman Zhang
 *
 */
public interface DeclareService extends CommonService{
	
	public List<TSAttachment> getAttachmentsByCode(String businessKey,String description);
	
}
