package sample.di.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import sample.di.business.valueobject.Product;
import sample.di.business.dataaccess.ProductDaoImpl;

@Component
public class ProductServiceImpl implements ProductService {
//    @Autowired
    private ProductDao productDao;

    @Autowired
    private ApplicationContext ac;

    @Override
    public Product findProduct() {
        // ApplicationContext を経由して dao を取得
        productDao = ac.getBean(ProductDaoImpl.class);
        return productDao.findProduct();
    }
}
