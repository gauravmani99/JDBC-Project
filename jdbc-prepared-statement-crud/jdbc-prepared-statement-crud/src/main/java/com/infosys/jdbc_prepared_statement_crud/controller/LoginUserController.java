package com.infosys.jdbc_prepared_statement_crud.controller;

import com.infosys.jdbc_prepared_statement_crud.dao.UserDao;
import com.infosys.jdbc_prepared_statement_crud.dto.User;

public class LoginUserController {

	public static void main(String[] args) {
		UserDao dao=new UserDao();
		String email = "mohan12345@gmail.com";
		String password = "mohan99000";
		User user=dao.getUserByEmailDao(email);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				System.out.println("login success");
			}else {
				System.out.println("login failed check your password");	
			}
		}else {
			System.out.println("email incorrect or something went wrong");
		}
	}
}
