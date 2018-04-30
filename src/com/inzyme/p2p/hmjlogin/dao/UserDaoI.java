package com.inzyme.p2p.hmjlogin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.inzyme.p2p.hmjlogin.entity.UserEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public interface UserDaoI {
	public boolean check(String username,String password,List<UserEntity> entities);

}
