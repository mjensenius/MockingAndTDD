/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contract.DTO;

import dk.cphbusiness.banking.interfaces.Account;
import java.util.Map;

/**
 *
 * @author Mikkel
 */
public class BankDTO {
    private String cvr;
    private String name;
    private Map<String, AccountDTO> accounts;

    public BankDTO(String cvr, String name, Map<String, AccountDTO> accounts){
        this.cvr = cvr;
        this.name = name;
        this.accounts = accounts;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
