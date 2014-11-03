package sample.di.business.dataaccess;

import org.springframework.stereotype.Component;
import sample.di.business.service.ProductDao;
import sample.di.business.valueobject.Product;

@Component
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product findProduct() {
        return new Product("ホチキス", 100);
    }
}
