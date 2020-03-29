package dk.cphbusiness.banking;

import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealBank;
import dk.cphbusiness.banking.implementations.RealCustomer;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;
import dk.cphbusiness.banking.interfaces.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CRUDTests {
    CRUDOperations crud;

    @Before
    public void initStartData() throws FileNotFoundException {
         crud = new CRUDOperations();
         crud.initDB();
    }
    @After
    public void tearDown() throws FileNotFoundException {
        crud.teardownDB();
    }
    @Test
    public void createAccount(){
        Bank bank = crud.getBankById(2);
        Customer customer = crud.getCustomerById(2);
        Account account = new RealAccount(bank, customer,"#1231");
        crud.createAccount(account);
        assertEquals(crud.getAccountById(3).getCustomer().getName(), "Jesper");
    }
    @Test
    public void getAccoountById(){
        Account account = crud.getAccountById(2);
        assertEquals(account.getNumber(), "123ABC");
    }
    
    @Test
    public void deleteAccountById() throws InterruptedException{
       crud.deleteAccountById(1);
       assertEquals(crud.getAccountById(1), null);
    }
    /*
    @Test
    public void updateBalanceForAccount(){
        Bank bank = crud.getBankById(2);
        Customer customer = crud.getCustomerById(2);
        Account account = new RealAccount(bank, customer,"#1231");
        crud.createAccount(account);
        assertEquals(crud.getAccountById(3).getCustomer().getName(), "Jesper");
    }
    @Test
    public void createBank(){
        Bank bank = crud.getBankById(2);
        Customer customer = crud.getCustomerById(2);
        Account account = new RealAccount(bank, customer,"#1231");
        crud.createAccount(account);
        assertEquals(crud.getAccountById(3).getCustomer().getName(), "Jesper");
    }
    @Test
    public void getBankById(){
        Bank bank = crud.getBankById(2);
        Customer customer = crud.getCustomerById(2);
        Account account = new RealAccount(bank, customer,"#1231");
        crud.createAccount(account);
        assertEquals(crud.getAccountById(3).getCustomer().getName(), "Jesper");
    }
    @Test
    public void createCustomer(){
        Bank bank = crud.getBankById(2);
        Customer customer = crud.getCustomerById(2);
        Account account = new RealAccount(bank, customer,"#1231");
        crud.createAccount(account);
        assertEquals(crud.getAccountById(3).getCustomer().getName(), "Jesper");
    }
    @Test
    public void updateCustomerName(){
        Bank bank = crud.getBankById(2);
        Customer customer = crud.getCustomerById(2);
        Account account = new RealAccount(bank, customer,"#1231");
        crud.createAccount(account);
        assertEquals(crud.getAccountById(3).getCustomer().getName(), "Jesper");
    }
    @Test
    public void createMovement(){
        Bank bank = crud.getBankById(2);
        Customer customer = crud.getCustomerById(2);
        Account account = new RealAccount(bank, customer,"#1231");
        crud.createAccount(account);
        assertEquals(crud.getAccountById(3).getCustomer().getName(), "Jesper");
    }
    */
    
    
    

    
    
    
}
