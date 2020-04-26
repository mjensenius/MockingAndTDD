package dk.cphbusiness.banking;

import dk.cphbusiness.banking.DataAccessLayer.CRUDFake;
import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealBank;
import dk.cphbusiness.banking.implementations.RealCustomer;
import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Account;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class DataAccessFakeTests {
    CRUDFake crud;

    @Before
    public void startTesting() {
        crud = new CRUDFake();
        crud.initData();
    }

    @Test
    public void createAccount() {
        RealBank bank1 = new RealBank(1,"1234","Sparekassen", new HashMap<String, Account>());
        RealCustomer customer1 = new RealCustomer(1,"123","Jesper",bank1);
        RealAccount account = new RealAccount(55, bank1, customer1, "ABC123", 44);
        crud.createAccount(account);
        assertEquals(4, crud.getAccounts().size());
    }

    @Test
    public void getAccountById() {
        Account account = crud.getAccountById(2);
        assertEquals("ABC1234", account.getNumber());
    }

    @Test
    public void deleteBankById() {
        RealBank bank = new RealBank(55, "123422", "Sparekassen3", new HashMap<String, Account>());
        crud.createBank(bank);
        crud.deleteBankById(55);
        assertEquals(3, crud.getBanks().size());
    }

    @Test
    public void createMovement() {
        RealMovement movement = new RealMovement(100, 2, 3);
        crud.createMovement(movement);
        assertEquals(1, crud.getMovements().size());
    }

    @Test
    public void updateCustomerName() {
        String newName = "kurt";
        RealCustomer customer = (RealCustomer) crud.getCustomers().get(0);
        crud.updateCustomerName(newName, customer);
        assertEquals(customer.getName(), newName);
    }
}
