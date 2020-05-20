package js.product.service;

import js.product.entity.Product;

import java.util.List;

public interface ProductService {

    int processProduct(Product product);

    List<Product> getAllProducts();
}
