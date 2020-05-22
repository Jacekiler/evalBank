package js.product.service;

import js.product.entity.Product;
import js.product.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public int saveProduct(Product product){
        return productDao.save(product);
    }

    public List<Product> getAllProducts(){
        return productDao.getAll();
    }
}
