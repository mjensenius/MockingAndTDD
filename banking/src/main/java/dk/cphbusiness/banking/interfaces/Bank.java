package dk.cphbusiness.banking.interfaces;

import dk.cphbusiness.banking.exceptions.NotFoundException;

import java.util.Map;


public interface Bank {
    int getId();

    String getCvr();

    String getName();

    Account getAccount(String number) throws NotFoundException;

    Map<String, Account> getAccounts(Customer customer);

    void addAccount(Account account);
}