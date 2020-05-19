package js.credit.repository;

import js.credit.model.entity.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditDaoImpl implements CreditDao{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public int save(Credit credit) {
        return (int) hibernateTemplate.save(credit);
    }

    @Override
    public List<Credit> getAll() {
        return hibernateTemplate.loadAll(Credit.class);
    }
}
