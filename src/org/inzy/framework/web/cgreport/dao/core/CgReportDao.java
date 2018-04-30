package org.inzy.framework.web.cgreport.dao.core;

import java.util.List;
import java.util.Map;

import org.inzy.framework.minidao.annotation.Arguments;
import org.inzy.framework.minidao.annotation.MiniDao;

/**
 * 
 * @author Auto-generator
 *
 */
@MiniDao
public interface CgReportDao{

	@Arguments("configId")
	List<Map<String,Object>> queryCgReportItems(String configId);
	
	@Arguments("id")
	Map queryCgReportMainConfig(String id);
}
