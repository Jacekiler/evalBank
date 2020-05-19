package js.product.controller;

import js.product.entity.Product;
import js.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/createProduct")
    public int createProduct(@RequestBody Product product){
        return productService.processProduct(product);
    }

    @GetMapping(value = "/getProducts")
    public Product[] getProducts(){
        return productService.getAllProducts();
    }

}
