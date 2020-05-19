package js.credit.controller;

import js.credit.model.entity.CreditDetails;
import js.credit.model.result.dto.CreditDTO;
import js.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @PostMapping(value = "/createCredit")
    public int createCredit(@Valid @RequestBody CreditDetails creditDetails){
        return creditService.processCredit(creditDetails);
    }

    @GetMapping(value = "/getCredits")
    public List<CreditDTO> getCredits() {
        return creditService.getAllCredits();
    }
}
