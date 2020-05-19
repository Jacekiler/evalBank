package js.credit.repository;



import js.credit.model.entity.Credit;

import java.util.List;

public interface CreditDao {

    int save(Credit credit);

    List<Credit> getAll();

}
