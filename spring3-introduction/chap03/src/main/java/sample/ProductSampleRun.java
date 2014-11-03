package sample;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.di.business.entity.Product;
import sample.di.business.service.ProductService;

public class ProductSampleRun {

    public static void main(String[] args) {
        ProductSampleRun productSampleRun = new ProductSampleRun();
        productSampleRun.execute();
    }

    private void execute() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        ProductService productService = ctx.getBean(ProductService.class);
        Product product = productService.findProduct();
        System.out.println(product);
    }
}
