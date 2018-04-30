package org.inzy.framework.web.demo.service.test;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.demo.entity.test.TFinanceEntity;
import org.inzy.framework.web.demo.entity.test.TFinanceFilesEntity;


public interface TFinanceServiceI extends CommonService{

	void deleteFile(TFinanceFilesEntity file);

	void deleteFinance(TFinanceEntity finance);

}
