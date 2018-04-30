package org.inzy.framework.web.demo.service.impl.test;

import java.util.List;

import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import org.inzy.framework.web.demo.entity.test.InzyOrderCustomEntity;
import org.inzy.framework.web.demo.entity.test.InzyOrderMainEntity;
import org.inzy.framework.web.demo.entity.test.InzyOrderProductEntity;
import org.inzy.framework.web.demo.service.test.InzyOrderMainServiceI;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("inzyOrderMainService")
@Transactional
public class InzyOrderMainServiceImpl extends CommonServiceImpl implements InzyOrderMainServiceI {

	
	public void addMain(InzyOrderMainEntity inzyOrderMain,
			List<InzyOrderProductEntity> inzyOrderProducList,
			List<InzyOrderCustomEntity> inzyOrderCustomList){
		//保存订单主信息
		this.save(inzyOrderMain);
		//保存订单产品明细
		for(InzyOrderProductEntity product:inzyOrderProducList){
			//外键设置
			product.setGoOrderCode(inzyOrderMain.getGoOrderCode());
			this.save(product);
		}
		//保存订单客户明细
		for(InzyOrderCustomEntity custom:inzyOrderCustomList){
			//外键设置
			custom.setGoOrderCode(inzyOrderMain.getGoOrderCode());
			this.save(custom);
		}
	}

	
	public void updateMain(InzyOrderMainEntity inzyOrderMain,
			List<InzyOrderProductEntity> inzyOrderProducList,
			List<InzyOrderCustomEntity> inzyOrderCustomList,
			boolean inzyOrderCustomShow) {
		//保存订单主信息
		this.saveOrUpdate(inzyOrderMain);
		//删除订单产品明细
		this.commonDao.deleteAllEntitie(this.findByProperty(InzyOrderProductEntity.class, "goOrderCode", inzyOrderMain.getGoOrderCode()));
		//保存订单产品明细
		for(InzyOrderProductEntity product:inzyOrderProducList){
			//外键设置
			product.setGoOrderCode(inzyOrderMain.getGoOrderCode());
			this.save(product);
		}
		if(inzyOrderCustomShow){
			//删除订单客户明细
			this.commonDao.deleteAllEntitie(this.findByProperty(InzyOrderCustomEntity.class, "goOrderCode", inzyOrderMain.getGoOrderCode()));
			//保存订单客户明细
			for(InzyOrderCustomEntity custom:inzyOrderCustomList){
				//外键设置
				custom.setGoOrderCode(inzyOrderMain.getGoOrderCode());
				this.save(custom);
			}
		}
	}

	
	public void delMain(InzyOrderMainEntity inzyOrderMain) {
		//删除主表信息
		this.delete(inzyOrderMain);
		//删除订单产品明细
		this.deleteAllEntitie(this.findByProperty(InzyOrderProductEntity.class, "goOrderCode", inzyOrderMain.getGoOrderCode()));
		//删除订单客户明细
		this.commonDao.deleteAllEntitie(this.findByProperty(InzyOrderCustomEntity.class, "goOrderCode", inzyOrderMain.getGoOrderCode()));
	}
}