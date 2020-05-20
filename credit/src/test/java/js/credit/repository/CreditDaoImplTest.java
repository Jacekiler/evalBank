package js.credit.repository;

import js.credit.model.entity.Credit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CreditDaoImplTest {

    private static final String CREDIT_NAME = "credit1";

    @Autowired
    private CreditDao creditDao;

    @Test
    void saveAndGetAll() {
        List<Credit> creditsBeforeSaving = creditDao.getAll();
        int creditid = creditDao.save(prepareCredit());
        List<Credit> creditsAfterSaving = creditDao.getAll();

        Assertions.assertEquals(creditsBeforeSaving.size(), 0);
        Assertions.assertEquals(creditsAfterSaving.size(), 1);
        Assertions.assertTrue(creditid > 0);
        validateCredit(creditsAfterSaving.get(0));
    }

    private void validateCredit(Credit credit){
        Assertions.assertEquals(credit.getName(), CREDIT_NAME);
    }

    private Credit prepareCredit(){
        Credit credit = new Credit();
        credit.setName(CREDIT_NAME);
        return credit;
    }
}