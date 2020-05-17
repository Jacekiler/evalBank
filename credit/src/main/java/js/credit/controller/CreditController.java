package js.credit.controller;

import js.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;


}
