package dk.cphbusiness.banking.DataAccessLayer;

import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealBank;
import dk.cphbusiness.banking.implementations.RealCustomer;
import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;
import dk.cphbusiness.banking.interfaces.Customer;
import dk.cphbusiness.banking.interfaces.Movement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CRUDFake implements DataAccessInterface {
    ArrayList<Account> accounts;
    ArrayList<Bank> banks;
    ArrayList<Movement> movements;
    ArrayList<Customer> customers;

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public ArrayList<Movement> getMovements() {
        return movements;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @Override
    public void createAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public Account getAccountById(int id) {
        Account acc = null;
        for (Account account : accounts) {
            if (account.getId() == id) {
                acc = account;
                return acc;
            }
        }
        return acc;
    }

    @Override
    public void deleteAccountById(int id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                accounts.remove(account);
            }
        }
    }

    @Override
    public void updateBalanceForAccount(int amount, Account account) {

    }

    @Override
    public void createBank(Bank bank) {
        banks.add(bank);
    }

    @Override
    public Bank getBankById(int id) {
        Bank bank = null;
        for (Bank banken : banks) {
            if (banken.getId() == id) {
                bank = banken;
                return bank;
            }
        }
        return bank;
    }

    @Override
    public void deleteBankById(int id) {
        for (Bank bank : banks) {
            if (bank.getId() == id) {
                banks.remove(bank);
            }
        }
    }

    @Override
    public void createCustomer(Customer customer) {
     customers.add(customer);
    }

    @Override
    public void updateCustomerName(String name, Customer customer) {

    }

    @Override
    public void createMovement(RealMovement movement) {
    movements.add(movement);
    }

    public void initData(){
        accounts = new ArrayList<>();
        banks = new ArrayList<>();
        customers = new ArrayList<>();
        movements = new ArrayList<>();
        RealBank bank1 = new RealBank(1,"123456789","Jyske Bank", new HashMap<String, Account>());
        RealBank bank2 = new RealBank(2,"987654321","Danske BanK",new HashMap<String, Account>());
        RealBank bank3 = new RealBank(3,"1234","Sparekassen", new HashMap<String, Account>());

        RealCustomer customer1 = new RealCustomer(1,"123","Jesper",bank1);
        RealCustomer customer2 = new RealCustomer(2,"1234","Bent",bank2);
        RealCustomer customer3 = new RealCustomer(3,"12345","Julie",bank3);
        RealCustomer customer4 = new RealCustomer(4, "1234335","pia",bank3);

        RealAccount account1 = new RealAccount(1,1,1,"ABC123",44);
        RealAccount account2 = new RealAccount(2,2,2,"ABC1234", 5);
        RealAccount account3 = new RealAccount(3,3,3,"ABC1234",100);

        createAccount(account1);
        createAccount(account2);
        createAccount(account3);

        createBank(bank1);
        createBank(bank2);
        createBank(bank3);

        createCustomer(customer1);
        createCustomer(customer2);
        createCustomer(customer3);
        createCustomer(customer4);

    }
}
