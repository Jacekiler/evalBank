package js.product.repository;

import js.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public int save(Product product) {
        return (int) hibernateTemplate.save(product);
    }

    @Override
    public List<Product> getAll() {
        return hibernateTemplate.loadAll(Product.class);
    }
}
