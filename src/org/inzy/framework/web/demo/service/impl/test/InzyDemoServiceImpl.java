package org.inzy.framework.web.demo.service.impl.test;

import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import org.inzy.framework.web.demo.service.test.InzyDemoServiceI;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("inzyDemoService")
@Transactional
public class InzyDemoServiceImpl extends CommonServiceImpl implements InzyDemoServiceI {
	
}
