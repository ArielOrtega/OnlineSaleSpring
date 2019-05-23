package com.yoyo.ventas.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yoyo.ventas.domain.Client;

@Repository
public class UserData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Client findByEmail(String email) {
		String selectSql = "Select c.id_end_user, c.first_name, c.login , c.password, r.id_rol, r.type_name as role_name from cliente c, userrole r" + 
				" where c.id_rol = r.id_rol and c.login = '" + email + "'";
		List<Client> clients = jdbcTemplate.query(selectSql, new RowMapper<Client>() {

			@Override
			public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
				Client client = new Client();
				client.setClienteId(rs.getInt("id_end_user"));
				client.setFirstName(rs.getString("first_name"));
				client.setLastName(rs.getString("last_name"));
				client.setUsername(rs.getString("login"));
				client.setPassword(rs.getString("password"));
				client.setSuscribed(rs.getBoolean("is_suscribed"));
				return client;
			}//mapRow
		});
		return (clients.isEmpty()? new Client() : clients.get(0));
	}//fin findByEmail
}
