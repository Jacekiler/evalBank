package js.product.repository;

import js.product.entity.entity.Product;

public interface ProductDao {

    int save(Product product);

    Product[] getAll();
}
