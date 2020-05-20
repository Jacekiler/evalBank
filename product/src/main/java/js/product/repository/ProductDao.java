package js.product.repository;


import js.product.entity.Product;

import java.util.List;

public interface ProductDao {

    int save(Product product);

    List<Product> getAll();
}
