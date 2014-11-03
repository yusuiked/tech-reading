package sample.di.business.repository;

import org.springframework.stereotype.Repository;
import sample.di.business.entity.Product;
import sample.di.business.service.ProductDao;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product findProduct() {
        return new Product("ホチキス", 100);
    }
}
