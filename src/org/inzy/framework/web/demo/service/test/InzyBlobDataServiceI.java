package org.inzy.framework.web.demo.service.test;

import org.inzy.framework.core.common.service.CommonService;
import org.springframework.web.multipart.MultipartFile;

public interface InzyBlobDataServiceI extends CommonService{
	public void saveObj(String documentTitle, MultipartFile file);

}
