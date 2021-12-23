// Created by antho
// Creation date 23-12-2021

package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.Bank;
import nl.hva.c25.team1.digivault.repository.BankDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private BankDAO bankDAO;

    @Autowired
    public BankService(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    public Bank vindBankOpId(int bankId) {
        return bankDAO.vindBankOpId(bankId);
    }

}