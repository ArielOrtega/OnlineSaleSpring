package com.yoyo.ventas.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.yoyo.ventas.domain.Category;
import com.yoyo.ventas.domain.Product;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDataTest {
		
		/**Autowired: Diciendole a fw que instancie la clase, y que cree la clase conexion**/
	@Autowired 
	private ProductData pData;

	@Test
	public void register() {
		//CALL `ecommerceyoyodb`.`yoyoProductRegister`
		//(3, 'NOT DELL INSP 15 3567 Ci3-7020U 4Gb 1TB DVD 15.6 HD Fog Grey', 500.000, 3, 'Dell Inspiron 15',@id_product );
		Category c = new Category();
		MultipartFile[] m = new MultipartFile[0];
		c.setCategoryId(3);
		//Product product = new Product("Dell Inspiron 15", "NOT DELL INSP 15 3567 Ci3-7020U 4Gb 1TB DVD 15.6 HD Fog Grey"
			//	, 500000,3, m,c);
		//pData.registerProduct(product);
		
	}
	
	@Test
	public void findAll() {
		List<Product> products = pData.findAll("Dell Inspiron");
		assertNotNull(products); //no puede ser nula
		assertTrue(!products.isEmpty()); 		
	}
	
	@Test
	public void getImages() {
		pData.getImagesProduct(67);
		assertNotNull(pData.getImagesProduct(67)); //no puede ser nula
		assertTrue(pData.getImagesProduct(67).length>0);
	}
	
	
}
	

