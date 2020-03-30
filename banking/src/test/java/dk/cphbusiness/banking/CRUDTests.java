package dk.cphbusiness.banking;

import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealBank;
import dk.cphbusiness.banking.implementations.RealCustomer;
import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;
import dk.cphbusiness.banking.interfaces.Customer;
import dk.cphbusiness.banking.interfaces.Movement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

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
        assertEquals( "Jesper", crud.getAccountById(3).getCustomer().getName());
    }
    @Test
    public void getAccoountById(){
        Account account = crud.getAccountById(2);
        assertEquals( "123ABC", account.getNumber());
    }
    
    @Test(expected = NullPointerException.class)
    public void deleteAccountById() throws InterruptedException{
        crud.deleteAccountById(1);
        Account acc = crud.getAccountById(1);
        assertEquals(NullPointerException.class, acc.getClass());
    }
    
    @Test
    public void updateBalanceForAccount(){
        Account account = crud.getAccountById(1);
        crud.updateBalanceForAccount(100, account);
        assertEquals( 100, account.getBalance());
    }
    
    @Test
    public void createBank(){
        Bank bank = new RealBank(3,"123456789", "Mathias & Mikkels guldbank");
        crud.createBank(bank);
        assertEquals("Mathias & Mikkels guldbank", crud.getBankById(3).getName());
    }
    
    @Test
    public void getBankById(){
        Bank bank = crud.getBankById(2);
        assertEquals("Danske Bank", bank.getName());
    }
    
    @Test
    public void createCustomer(){
        Bank bank = crud.getBankById(2);
        Customer customer = new RealCustomer("10101020", "Helene", bank);
        crud.createCustomer(customer);
        assertEquals("Helene", crud.getCustomerById(3).getName());
    }
    
    @Test
    public void updateCustomerName(){
        Customer customer = (RealCustomer) crud.getCustomerById(2);
        crud.updateCustomerName("Peter", customer);
        assertEquals("Peter", crud.getAccountById(2).getCustomer().getName());
    }
    @Test
    public void getMovementsByAccountId(){
        List<Movement> movements = crud.getMovementsByAccountId(1);
        assertEquals(1,movements.size());
    }
    
    @Test
    public void createMovement(){
        Movement movement = new RealMovement(100, 1, 2);
        crud.createMovement(movement);
        List<Movement> movements = crud.getMovementsByAccountId(1);
        assertEquals(2, movements.size());
    }  
    
}
