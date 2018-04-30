package org.inzy.framework.web.demo.service.impl.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.inzy.framework.core.common.service.impl.CommonServiceImpl;
import org.inzy.framework.web.demo.service.test.InzyNoteServiceI;

@Service("inzyNoteService")
@Transactional
public class InzyNoteServiceImpl extends CommonServiceImpl implements InzyNoteServiceI {
	
}