package org.inzy.framework.web.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import org.inzy.framework.web.system.service.TimeTaskServiceI;


@Service("timeTaskService")
@Transactional
public class TimeTaskServiceImpl extends CommonServiceImpl implements TimeTaskServiceI {
	
}