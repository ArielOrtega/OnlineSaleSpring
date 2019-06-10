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

import com.yoyo.ventas.domain.Category;


@Repository
public class CategoryData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	@Autowired
	private DataSource dataSource;
	
	public List<Category> findAll(){
		Connection conexion = null;
		List<Category> categories;
		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			CallableStatement csCategories = conexion.prepareCall("{call yoyoGetCategories()}");
			csCategories.execute();
			
			conexion.commit();
			
			ResultSet rs = csCategories.getResultSet(); 
			
			categories = new ArrayList<>();
			Category category = new Category();
			while(rs.next()) {
				category.setCategoryId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));			
				categories.add(category);
				category = new Category();
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
		return categories;		
	}
}
