package org.inzy.framework.web.demo.service.test;

import org.inzy.framework.core.common.model.json.DataGrid;
import org.inzy.framework.core.common.service.CommonService;
import org.inzy.framework.web.demo.entity.test.InzyJdbcEntity;

import net.sf.json.JSONObject;


public interface InzyJdbcServiceI extends CommonService{
	public void getDatagrid1(InzyJdbcEntity pageObj, DataGrid dataGrid);
	public void getDatagrid2(InzyJdbcEntity pageObj, DataGrid dataGrid);
	public JSONObject getDatagrid3(InzyJdbcEntity pageObj, DataGrid dataGrid);
	public void listAllByJdbc(DataGrid dataGrid);
}
