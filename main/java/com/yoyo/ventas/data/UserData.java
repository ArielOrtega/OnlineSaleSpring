package com.yoyo.ventas.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.yoyo.ventas.domain.Client;
import com.yoyo.ventas.domain.Employee;
import com.yoyo.ventas.domain.Role;

@Repository
public class UserData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	@Autowired
	private DataSource dataSource;
	
	public Client findByEmail(String email) {
		String selectSql = "Select c.id_end_user, c.is_suscribed, c.last_name, c.first_name, c.username , c.password, r.id_rol, r.type_name as role_name from client c, userrole r" + 
				" where c.id_rol = r.id_rol and c.username = '" + email + "'";
		List<Client> clients = jdbcTemplate.query(selectSql, new RowMapper<Client>() {

			@Override
			public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
				Client client = new Client();
				client.setClienteId(rs.getInt("id_end_user"));
				client.setFirstName(rs.getString("first_name"));
				client.setLastName(rs.getString("last_name"));
				client.setUsername(rs.getString("username"));
				client.setPassword(rs.getString("password"));
				client.setSuscribed(rs.getBoolean("is_suscribed"));
				do {
					if(rs.getString("username").equals(client.getUsername())) {
						Role role = new Role();
						role.setRolId(rs.getInt("id_rol"));
						role.setTypeName(rs.getString("role_name"));
						client.getRoles().add(role);
					}
				}while(rs.next());
				return client;
			}//mapRow
		});
		return (clients.isEmpty()? new Client() : clients.get(0));
	}//fin findByEmail
	
	public Employee findEmployeeByEmail(String email) {
		String selectSql = "Select c.id_employee, c.last_name, c.first_name, c.username , c.password, r.id_rol, r.type_name as role_name from employee c, userrole r" + 
				" where c.rol_id = r.id_rol and c.username = '" + email + "'";
		List<Employee> employees = jdbcTemplate.query(selectSql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee  = new Employee();
				employee .setEmployeeId(rs.getInt("id_employee"));
				employee .setFirstName(rs.getString("first_name"));
				employee .setLastName(rs.getString("last_name"));
				employee .setUsername(rs.getString("username"));
				employee .setPassword(rs.getString("password"));
				do {
					if(rs.getString("username").equals(employee.getUsername())) {
						Role role = new Role();
						role.setRolId(rs.getInt("id_rol"));
						role.setTypeName(rs.getString("role_name"));
						employee.getRoles().add(role);
					}
				}while(rs.next());
				return employee;
			}//mapRow
		});
		return (employees.isEmpty()? new Employee() : employees.get(0));
	}//fin findByEmail
	
}
