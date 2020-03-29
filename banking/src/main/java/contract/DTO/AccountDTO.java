/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contract.DTO;

import dk.cphbusiness.banking.interfaces.Bank;
import java.util.List;

/**
 *
 * @author Mikkel
 */

public class AccountDTO {
    private BankDTO bank;
    private CustomerDTO customer;
    private String number;
    private int balance;
    private List<MovementDTO> withdrawals;
    private List<MovementDTO> deposits;

    public AccountDTO(BankDTO bank, CustomerDTO customer, String number, int balance, List<MovementDTO> withdrawals, List<MovementDTO> deposits) {
        this.bank = bank;
        this.customer = customer;
        this.balance = balance;
        this.withdrawals = withdrawals;
        this.deposits = deposits;
        this.number = number;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<MovementDTO> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<MovementDTO> withdrawals) {
        this.withdrawals = withdrawals;
    }

    public List<MovementDTO> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<MovementDTO> deposits) {
        this.deposits = deposits;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }
}