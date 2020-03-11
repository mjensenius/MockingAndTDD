package dk.cphbusiness.banking.implementations;

import dk.cphbusiness.banking.exceptions.NotFoundException;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;
import dk.cphbusiness.banking.interfaces.Customer;

import java.util.Map;

public class RealBank implements Bank {

    private final String cvr;
    private final String name;
    private final Map<String, Account> accounts;

    public RealBank(String cvr, String name, Map<String, Account> accounts) {
        this.cvr = cvr;
        this.name = name;
        this.accounts = accounts;
    }

    @Override
    public String getCvr() {
        return cvr;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Account getAccount(String number) throws NotFoundException {
        Account account = accounts.get(number);
        if (account == null) {
            throw new NotFoundException("Account: " + number + " does not exist");
        }
        return account;
    }

    @Override
    public void addAccount(Account account) {
        accounts.put(account.getNumber(), account);
        account.getCustomer().addAccount(account);
    }

    @Override
    public Map<String, Account> getAccounts(Customer customer) {
        return customer.getAccounts();
    }
}