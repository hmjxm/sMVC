package org.inzy.framework.web.demo.service.test;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.demo.entity.test.WebOfficeEntity;

import org.springframework.web.multipart.MultipartFile;

public interface WebOfficeServiceI extends CommonService{
	public void saveObj(WebOfficeEntity docObj, MultipartFile file);
}
