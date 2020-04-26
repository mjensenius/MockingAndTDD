/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contract.DTO;

import dk.cphbusiness.banking.interfaces.Account;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mikkel
 */
public class CustomerDTO {
    private String cpr;
    private String name;
    private BankDTO bank;
    private Map<String, AccountDTO> accounts = new HashMap<>();

    public CustomerDTO(String cpr, String name, BankDTO bank, Map<String, AccountDTO> accounts) {
        this.cpr = cpr;
        this.name = name;
        this.bank = bank;
        this.accounts = accounts;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }

    public Map<String, AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
