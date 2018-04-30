package com.inzyme.p2p.hmjlogin.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.inzyme.p2p.hmjlogin.entity.UserEntity;

public interface UserserviceI {
	public boolean login(String username,String password,List<UserEntity> entities) ;
}
