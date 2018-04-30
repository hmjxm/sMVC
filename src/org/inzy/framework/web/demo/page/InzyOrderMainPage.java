package org.inzy.framework.web.demo.page;

import java.util.ArrayList;
import java.util.List;

import org.inzy.framework.web.demo.entity.test.InzyOrderCustomEntity;
import org.inzy.framework.web.demo.entity.test.InzyOrderProductEntity;

/**   
 * @Title: Entity
 * @Description: 订单信息 VO
 * @author Goodman Zhang
 * @date 2013-03-19 22:01:34
 * @version V1.0   
 *
 */
@SuppressWarnings("serial")
public class InzyOrderMainPage implements java.io.Serializable {
	/**订单客户明细*/
	private List<InzyOrderCustomEntity> inzyOrderCustomList = new ArrayList<InzyOrderCustomEntity>();
	public List<InzyOrderCustomEntity> getInzyOrderCustomList() {
		return inzyOrderCustomList;
	}
	public void setInzyOrderCustomList(List<InzyOrderCustomEntity> inzyOrderCustomList) {
		this.inzyOrderCustomList = inzyOrderCustomList;
	}
	/**订单产品明细*/
	private List<InzyOrderProductEntity> inzyOrderProductList = new ArrayList<InzyOrderProductEntity>();
	public List<InzyOrderProductEntity> getInzyOrderProductList() {
		return inzyOrderProductList;
	}
	public void setInzyOrderProductList(List<InzyOrderProductEntity> inzyOrderProductList) {
		this.inzyOrderProductList = inzyOrderProductList;
	}
}
