package dk.cphbusiness.banking.implementations;

import dk.cphbusiness.banking.exceptions.NotFoundException;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;
import dk.cphbusiness.banking.interfaces.Customer;
import dk.cphbusiness.banking.interfaces.Movement;

import java.util.ArrayList;
import java.util.List;


public class RealAccount implements Account {
    private int id;
    private Bank bank;
    private Customer customer;
    private int bankid;
    private int customerid;
    private final String number;
    private long balance = 0;
    private List<Movement> withdrawals;
    private List<Movement> deposits;

    public RealAccount(Bank bank, Customer customer, String number) {
        this.bank = bank;
        this.customer = customer;
        this.number = number;
        this.withdrawals = new ArrayList<>();
        this.deposits = new ArrayList<>();
    }

    public RealAccount(int id, int bankid, int customerid, String accountNumber, int balance) {
        this.id = id;
        this.bankid = bankid;
        this.customerid = customerid;
        this.number = accountNumber;
        this.balance = balance;
    }

    @Override
    public void transfer(long amount, Account target) throws NotFoundException {
        if (target == null) {
            throw new NotFoundException("Account: does not exist!");
        }

        this.withdraw(amount);
        target.deposit(amount);
    }

    @Override
    public void transfer(long amount, String targetNumber) throws NotFoundException {
        Account target = bank.getAccount(targetNumber);
        if (target == null) {
            throw new NotFoundException("Account: " + targetNumber + " does not exist");
        }
        transfer(amount, target);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public List<Movement> getWithdrawals() {
        return withdrawals;

    }

    @Override
    public List<Movement> getDeposits() {
        return deposits;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    @Override
    public void deposit(long amount) {
        this.deposits.add(new RealMovement(amount));
        balance += amount;
    }

    @Override
    public void withdraw(long amount) {
        this.withdrawals.add(new RealMovement(-amount));
        balance += -amount;
    }
}