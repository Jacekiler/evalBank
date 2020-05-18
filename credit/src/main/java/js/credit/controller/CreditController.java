package js.credit.controller;

import js.credit.entity.Credit;
import js.credit.entity.CreditDetails;
import js.credit.repository.CreditDao;
import js.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PostMapping(value = "/createCredit")
    public int createCredit(@RequestBody CreditDetails creditDetails){
        return creditService.processCredit(creditDetails);
    }

    @GetMapping(value = "/getCredits")
    public List<CreditDetails> getCredits(){
        return creditService.getAllCredits();
    }

}
