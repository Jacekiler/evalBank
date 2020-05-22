package js.credit.service;

import js.credit.model.inout.dto.CreditDetailsDTO;

import java.util.List;

public interface CreditService {

     int processCredit(CreditDetailsDTO creditDetails);

     List<CreditDetailsDTO> getAllCredits();
}
