package com.yoyo.ventas.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yoyo.ventas.domain.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDataTest {
	
	@Autowired 
	private CategoryData cData;

	@Test
	public void findAll() {
		List<Category> categories = cData.findAll();
		assertNotNull(categories); //no puede ser nula
		assertTrue(!categories.isEmpty()); 
	}

}
