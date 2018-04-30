package com.inzyme.p2p.hmjlogin.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.inzy.framework.core.common.dao.ICommonDao;
import org.inzy.framework.core.common.model.common.DBTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inzyme.p2p.hmjlogin.dao.UserDaoI;
import com.inzyme.p2p.hmjlogin.entity.UserEntity;
import com.inzyme.p2p.hmjlogin.service.UserserviceI;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Service("userservice")
@Transactional
public class UserserviceImpl implements UserserviceI{
	@Autowired
	public UserDaoI dao=null;

	public boolean login(String username,String password,List<UserEntity> entities)  {
		return  dao.check(username, password,entities);
	}
}
