package js.product.repository;

import js.product.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductDaoImplTest {

    private static final String PRODUCT_NAME = "product1";

    @Autowired
    private ProductDao productDao;

    @Test
    void saveAndGetAll() {
        List<Product> productsBeforeSaving = productDao.getAll();
        int productid = productDao.save(prepareProduct());
        List<Product> productsAfterSaving = productDao.getAll();

        Assertions.assertEquals(productsBeforeSaving.size(), 0);
        Assertions.assertEquals(productsAfterSaving.size(), 1);
        Assertions.assertTrue(productid > 0);
        validateProduct(productsAfterSaving.get(0));
    }

    private void validateProduct(Product product){
        Assertions.assertEquals(product.getProductName(), PRODUCT_NAME);
    }

    private Product prepareProduct(){
        Product product = new Product();
        product.setProductName(PRODUCT_NAME);
        return product;
    }
}