package com.team1.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.team1.data.User;


public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public String register (final User user){
		
		final String checkSql = "select * from user where name=? and email=?";
		try{
			User tempUser = jdbcTemplate.queryForObject(checkSql, new Object[]{user.getUser(), user.getMail()}, new UserRowMapper());
			
			return "User already exists!!!";
		
		}catch(EmptyResultDataAccessException e){
			
		
		
			final String sql = "insert into user(name,email) values(?,?)";
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getUser());
					ps.setString(2, user.getMail());
					return ps;
				}
			}, holder);
			
			int newUserId = holder.getKey().intValue();
			user.setId(newUserId);
			return "Your User ID: "+ user.getId();
		
		
		}
	}
}


class UserRowMapper implements RowMapper<User>
{
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException{
		User user = new User();
		user.setUser(rs.getString("name"));
		user.setMail(rs.getString("email"));
		return user;
	}
}
