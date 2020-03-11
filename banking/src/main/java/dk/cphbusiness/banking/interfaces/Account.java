package dk.cphbusiness.banking.interfaces;

import dk.cphbusiness.banking.exceptions.NotFoundException;

import java.util.List;


public interface Account {

    Bank getBank();

    Customer getCustomer();

    String getNumber();

    void transfer(long amount, Account target) throws NotFoundException;

    void transfer(long amount, String targetNumber) throws NotFoundException;

    long getBalance();

    List<Movement> getWithdrawals();

    List<Movement> getDeposits();

    void deposit(long amount);

    void withdraw(long amount);

}