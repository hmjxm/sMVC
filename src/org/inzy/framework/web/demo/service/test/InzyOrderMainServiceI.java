package org.inzy.framework.web.demo.service.test;

import java.util.List;

import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.demo.entity.test.InzyOrderCustomEntity;
import org.inzy.framework.web.demo.entity.test.InzyOrderMainEntity;
import org.inzy.framework.web.demo.entity.test.InzyOrderProductEntity;



public interface InzyOrderMainServiceI extends CommonService{
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(InzyOrderMainEntity inzyOrderMain,List<InzyOrderProductEntity> inzyOrderProducList,List<InzyOrderCustomEntity> inzyOrderCustomList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(InzyOrderMainEntity inzyOrderMain,List<InzyOrderProductEntity> inzyOrderProducList,List<InzyOrderCustomEntity> inzyOrderCustomList,boolean inzyOrderCustomShow) ;
	public void delMain (InzyOrderMainEntity inzyOrderMain);
}
