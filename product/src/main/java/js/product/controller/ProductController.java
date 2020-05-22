package js.product.controller;

import js.product.entity.Product;
import js.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping(value = "/createProduct")
    public int createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping(value = "/getProducts")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

}
