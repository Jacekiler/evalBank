package js.credit.service;

import js.credit.repository.CreditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

    @Autowired
    private CreditDao creditDao;


}
