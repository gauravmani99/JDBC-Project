package com.infosys.jdbc_prepared_statement_crud.controller;

import java.time.LocalDate;

import com.infosys.jdbc_prepared_statement_crud.dao.UserDao;
import com.infosys.jdbc_prepared_statement_crud.dto.User;

public class InsertUserController {

	public static void main(String[] args) {
		UserDao dao = new UserDao();
		
		User user=new User();
		user.setId(666);
		user.setName("mohan");
		user.setEmail("mohan12345@gmail.com");
		user.setPassword("mohan9900");
		user.setGender("MALE");
		user.setDob(LocalDate.parse("1997-12-29"));
		
		User user2=dao.saveUserDao(user);
		
		String msg=user2!=null?"saved":"not saved";
		
		System.out.println(msg);
	}
}
