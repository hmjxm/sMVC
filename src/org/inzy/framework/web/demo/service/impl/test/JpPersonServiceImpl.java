package org.inzy.framework.web.demo.service.impl.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import org.inzy.framework.web.demo.service.test.JpPersonServiceI;

@Service("jpPersonService")
@Transactional
public class JpPersonServiceImpl extends CommonServiceImpl implements JpPersonServiceI {
	
}