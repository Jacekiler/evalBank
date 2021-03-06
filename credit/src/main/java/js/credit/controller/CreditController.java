package js.credit.controller;

import js.credit.model.inout.dto.CreditDetailsDTO;
import js.credit.service.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/credit")
public class CreditController {

    @Autowired
    private CreditServiceImpl creditService;

    @PostMapping(value = "/createCredit")
    public int createCredit(@Valid @RequestBody CreditDetailsDTO creditDetailsDTO){
        return creditService.processCredit(creditDetailsDTO);
    }

    @GetMapping(value = "/getCredits")
    public List<CreditDetailsDTO> getCredits() {
        return creditService.getAllCredits();
    }
}
