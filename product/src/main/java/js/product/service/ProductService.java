package js.product.service;

import js.product.entity.entity.Product;
import js.product.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public int processProduct(Product product){
        return productDao.save(product);
    }

    public Product[] getAllProducts(){
        return productDao.getAll();
    }
}
