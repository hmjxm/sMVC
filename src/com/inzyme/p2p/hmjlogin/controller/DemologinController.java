package com.inzyme.p2p.hmjlogin.controller;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.inzy.framework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inzyme.p2p.finance.account.entity.AccountEntity;
import com.inzyme.p2p.finance.account.service.AccountServiceI;
import com.inzyme.p2p.hmjlogin.entity.UserEntity;
import com.inzyme.p2p.hmjlogin.service.UserserviceI;

@Controller
@RequestMapping("/demologinController")
public class DemologinController {
	@Autowired
	private SystemService systemService;
	@Autowired
	private UserserviceI userService;
	
	@RequestMapping(params = "show")
	public ModelAndView show(HttpServletRequest request) {
		return new ModelAndView("com/demo/demomain");
	}
	
	@RequestMapping(params = "login")
	public ModelAndView login(HttpServletRequest request) throws SQLException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		List<UserEntity> list=systemService.loadAll(UserEntity.class);
		boolean flag = userService.login(username, password, list);
		if(flag==true)
		{
		    return new ModelAndView("com/demo/success");
		}
		else
		{
			return new ModelAndView("com/demo/fail");
		}
	}
	
	
	@RequestMapping(params = "find")
	@ResponseBody
	public List<AccountEntity> find(HttpServletRequest request) {
		List<AccountEntity> list=systemService.loadAll(AccountEntity.class);
		System.out.println(list);
		return list;
	}
}
