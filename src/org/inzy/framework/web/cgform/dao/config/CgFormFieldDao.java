package org.inzy.framework.web.cgform.dao.config;

import java.util.List;
import java.util.Map;

import org.inzy.framework.minidao.annotation.Arguments;
import org.inzy.framework.minidao.annotation.MiniDao;

/**
 * 
 * @Title:CgFormFieldDao
 * @description:
 * @author Goodman Zhang
 * @date Aug 24, 2013 11:33:33 AM
 * @version V1.0
 */
@MiniDao
public interface CgFormFieldDao {
	
	@Arguments("tableName")
	public List<Map<String, Object>> getCgFormFieldByTableName(String tableName);
	
	@Arguments("tableName")
	public List<Map<String, Object>> getCgFormHiddenFieldByTableName(String tableName);
	
}
