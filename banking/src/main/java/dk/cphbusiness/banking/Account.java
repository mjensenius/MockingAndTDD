package dk.cphbusiness.banking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account implements AccountInterface {
  private Bank bank;
  private Customer customer;
  private String number;
  private long balance = 0;
  private List<Movement> withdrawals = new ArrayList<>();
  private List<Movement> deposits = new ArrayList<>();

  public Account(Bank bank, Customer customer, String number) {
    this.bank = bank;
    this.customer = customer;
    this.number = number;
  }

  public Bank getBank() {
    return bank;
  }

  public List<Movement> getDeposits() {
    return deposits;
  }

  public List<Movement> getWithdrawals() {
    return withdrawals;
  }

  public Customer getCustomer() {
    return customer;
  }

  public String getNumber() {
    return number;
  }

  public long getBalance() {
    return balance;
  }

  public void transfer(long amount, Account target) {
    balance -= amount;
    target.balance += amount;
    Date date = new Date();
    long milliseconds = date.getTime();

    deposits.add(new Movement(this, target, amount, milliseconds));
    withdrawals.add(new Movement(target, this, amount, milliseconds));
  }

  public void transfer(long amount, String targetNumber) {
    Account target = bank.getAccount(targetNumber);
    transfer(amount, target);

  }
}
