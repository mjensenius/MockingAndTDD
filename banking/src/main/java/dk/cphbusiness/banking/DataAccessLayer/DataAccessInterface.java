package dk.cphbusiness.banking.DataAccessLayer;

import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;
import dk.cphbusiness.banking.interfaces.Customer;

public interface DataAccessInterface {
    void createAccount(Account account);
    Account getAccountById(int id);
    void deleteAccountById(int id);
    void updateBalanceForAccount(int amount, Account account);
    void createBank(Bank bank);
    Bank getBankById(int id);
    void deleteBankById(int id);
    void createCustomer(Customer customer);
    void updateCustomerName(String name, Customer customer);
    void createMovement(RealMovement movement);
}
