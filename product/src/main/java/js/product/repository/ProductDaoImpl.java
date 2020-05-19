package js.product.repository;

import js.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public int save(Product product) {
        return (int) hibernateTemplate.save(product);
    }

    @Override
    public Product[] getAll() {
        List<Product> productList = hibernateTemplate.loadAll(Product.class);
        Product[] productsArray = new Product[productList.size()];
        productList.toArray(productsArray);
        return productsArray;
    }
}
