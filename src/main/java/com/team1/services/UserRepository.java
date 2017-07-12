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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.team1.data.Order;
import com.team1.data.User;

@Repository
public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public String register (final User user){
		System.out.println(user.getEmail() + user.getName());
		final String checkSql = "select * from user where email=?";
		try{
			User tempUser = jdbcTemplate.queryForObject(checkSql, new Object[]{user.getEmail()}, new UserRowMapper());
			
			return "User already exists!!!";
		
		}catch(EmptyResultDataAccessException e){
			
		
		
			final String sql = "insert into user(name,email) values(?,?)";
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					ps.setString(1, user.getName());
					ps.setString(2, user.getEmail());
					return ps;
				}
			}, holder);
			
			int newUserId = holder.getKey().intValue();
			user.setId(newUserId);
			return "Your User ID: "+ user.getId();
		
		
		}
	}
	
	@Transactional(readOnly=true)
	public User getUserTransactions(int userId) {
		// TODO Auto-generated method stub
		
		
		return jdbcTemplate.queryForObject("select * from transaction where user_id=?", new Object[]{userId}, new UserRowMapper());
		
	}
	
	
}


class UserRowMapper implements RowMapper<User>
{
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException{
		User user = new User();
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		return user;
	}
}
