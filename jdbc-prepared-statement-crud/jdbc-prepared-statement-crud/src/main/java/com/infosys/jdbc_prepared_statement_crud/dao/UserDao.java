package com.infosys.jdbc_prepared_statement_crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.infosys.jdbc_prepared_statement_crud.connection.UserConnection;
import com.infosys.jdbc_prepared_statement_crud.dto.User;

public class UserDao {

	Connection connection = UserConnection.getUserConnection();

	public User saveUserDao(User user) {

		String insertUserQuery = "insert into user(id,name,email,password,gender,dob) values(?,?,?,?,?,?)";

		try {
			if (connection != null) {
				PreparedStatement ps = connection.prepareStatement(insertUserQuery);

				ps.setInt(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPassword());
				ps.setString(5, user.getGender());
				ps.setObject(6, user.getDob());

				return ps.executeUpdate() > 0 ? user : null;
			} else {
				System.out.println("no connection found!!!");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> getAllUserDao() {
		String selectUserQuery = "select * from user";

		try {
			PreparedStatement ps = connection.prepareStatement(selectUserQuery);

			ResultSet set = ps.executeQuery();

			List<User> users = new ArrayList<User>();

			while (set.next()) {
				int id = set.getInt("id");
				String name = set.getString("name");
				String email = set.getString("email");
				String password = set.getString("password");
				String gender = set.getString("gender");
				LocalDate dob = set.getDate("dob").toLocalDate();

				User user = new User();
				user.setId(id);
				user.setName(name);
				user.setEmail(email);
				user.setPassword(password);
				user.setGender(gender);
				user.setDob(dob);
				// add user object to collection users object
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User getUserByEmailDao(String email) {

		String selectUserQuery = "select * from user where email=?";

		try {
			PreparedStatement ps = connection.prepareStatement(selectUserQuery);

			ps.setString(1, email);
			
			ResultSet set = ps.executeQuery();

			if (set.next()) {
				
				String emailDb = set.getString("email");
				String password = set.getString("password");
				
				User user = new User();
				user.setEmail(email);
				user.setPassword(password);
				
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
