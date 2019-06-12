package com.yoyo.ventas.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.yoyo.ventas.domain.ShoppingCart;

@Repository
public class ShoppingCartData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserData userData;
	
	public int saveShoppingCart(ShoppingCart shoppingCart) {
		//`yoyoInsertShoppingCartById`(id_client_param int, id_product_param int, quantity_param int)
		Connection conexion = null;
		int productId;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csInsertShoppingCart = conexion.prepareCall("{call yoyoInsertShoppingCartById(?, ?, ?)}");
			
			csInsertShoppingCart.setInt(2, shoppingCart.getProduct().getProductId());
			csInsertShoppingCart.setInt(3, shoppingCart.getQuantity());
			
			//obtener la info del cliente, si esta loggeado
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			int clientId = userData.findByEmail(auth.getPrincipal().toString()).getClienteId();
			
			csInsertShoppingCart.setInt(1, clientId);
			
			csInsertShoppingCart.execute();
			
			conexion.commit();
			
			productId = shoppingCart.getProduct().getProductId();
			
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}finally {
			if(conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		} 
		return productId;
	}
	
	public ShoppingCart getShoppingCart(int idClient) {
		
		return null;
	}
}
