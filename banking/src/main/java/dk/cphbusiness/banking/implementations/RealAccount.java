package dk.cphbusiness.banking.implementations;

import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
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

    public RealAccount(int id, Bank bank, Customer customer, String number, int balance) {
        this.id = id;
        this.bank = bank;
        this.customer = customer;
        this.number = number;
        this.balance = balance;
        this.withdrawals = new ArrayList<>();
        this.deposits = new ArrayList<>();
    }


    @Override
    public void transfer(long amount, Account target) throws NotFoundException {
        if (target == null) {
            throw new NotFoundException("Account: does not exist!");
        }
        this.withdraw(amount,target.getId());
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

    public void setWithdrawals(List<Movement> withdrawals) {
        this.withdrawals = withdrawals;
    }

    public void setDeposits(List<Movement> deposits) {
        this.deposits = deposits;
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

    public void withdraw(long amount, int targetId) {
        RealMovement movement = new RealMovement((int) -amount,targetId,this.id);
        this.withdrawals.add(movement);
        balance += -amount;
    }
}