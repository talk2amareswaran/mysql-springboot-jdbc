package com.talk2amareswaran.projects.mysqldemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class UserServiceDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Users> getUsers() {
		List<Users> usersList = new ArrayList<>();
		String sql = "SELECT * FROM USERS";
		Collection<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        rows.stream().map((row) -> {
        	Users users = new Users();
        	users.setName((String) row.get("NAME"));
        	users.setAge(String.valueOf(row.get("AGE")));
        	return users;
        }).forEach((user) -> {
        	usersList.add(user);
        });
		return usersList;
	}

	public void createUser(Users user) {
		
		jdbcTemplate.update((Connection connection) -> {
			PreparedStatement  preparedStatement = connection.prepareStatement("INSERT INTO USERS (NAME,AGE) VALUES (?,?)");
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getAge());
	        return preparedStatement;
		});
		
	}
}
