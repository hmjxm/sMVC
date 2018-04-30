package com.inzyme.p2p.hmjlogin.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.inzyme.p2p.hmjlogin.dao.UserDaoI;
import com.inzyme.p2p.hmjlogin.entity.UserEntity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@Repository("userdao")
public class UserDaoImpl implements UserDaoI{

	public boolean check(String username,String password,List<UserEntity> entities){
		boolean flag=false;
		for(int i=0;i<entities.size();i++)
		{
			if(username.equals(entities.get(i).getUname())&&password.equals(entities.get(i).getPwd()))
			{
				flag=true;
				break;
			}
			flag=false;
		}
		return flag;
	}

}
