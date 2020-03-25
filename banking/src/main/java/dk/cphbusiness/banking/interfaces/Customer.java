package dk.cphbusiness.banking.interfaces;

import dk.cphbusiness.banking.exceptions.NotFoundException;

import java.util.List;
import java.util.Map;


public interface Customer {
    int getId();

    void transfer(long amount, Account account, Account targetAccount) throws NotFoundException;

    String getCpr();

    String getName();

    Bank getBank();

    Map<String, Account> getAccounts();

    void addAccount(Account account);

    List<Movement> getListOfWithdrawal(String accNumber) throws NotFoundException;

    List<Movement> getListOfDeposits(String accNumber) throws NotFoundException;
}