package com.inzyme.demo.imgupload.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inzyme.demo.imgupload.service.ImgUploadServiceI;

import org.inzy.framework.core.common.service.impl.CommonServiceImpl;

@Service("imgUploadService")
@Transactional
public class ImgUploadServiceImpl extends CommonServiceImpl implements ImgUploadServiceI {
	
}