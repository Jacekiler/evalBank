package js.credit.service;

import js.credit.model.entity.CreditDetails;
import js.credit.model.result.dto.CreditDTO;

import java.util.List;

public interface CreditService {

     int processCredit(CreditDetails creditDetails);

     List<CreditDTO> getAllCredits();
}
