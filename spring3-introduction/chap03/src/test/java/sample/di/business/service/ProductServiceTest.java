package sample.di.business.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.di.business.entity.Product;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    public void testFindProduct() throws Exception {
        Product product = productService.findProduct();
        System.out.println(product);
    }
}
