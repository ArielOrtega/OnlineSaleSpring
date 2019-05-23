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
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.yoyo.ventas.domain.Product;

@Repository
public class ProductData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	@Autowired
	private DataSource dataSource;
	
	public List<Product> findTop(){
		Connection conexion = null;
		List<Product> products;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csProducts = conexion.prepareCall("{call yoyoGetTop12()}");
			csProducts.execute();
			
			conexion.commit();
			
			ResultSet rs = csProducts.getResultSet(); 
			
			products = new ArrayList<>();
			Product product = new Product();
			while(rs.next()) {
				product.setProductId(rs.getInt(1));
				product.getCategory().setCategoryId(rs.getInt(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getFloat(4));
				product.setStockUnits(rs.getFloat(5));
				product.setProductName(rs.getString(6));
				products.add(product);
				product = new Product();
			}
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
		return products;
	}
}
