package js.credit.repository;

import js.credit.entity.Credit;

import java.util.List;

public interface CreditDao {

    int save(Credit credit);

    List<Credit> getAll();

}
