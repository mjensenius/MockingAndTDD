package dk.cphbusiness.banking;

import dk.cphbusiness.banking.DataAccessLayer.CRUDFake;
import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealMovement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataAccessFakeTests {
    CRUDFake crud;

    @Before
    public void startTesting() {
        //initiating start data with 3 accounts, 3 banks and 4 customers
        crud = new CRUDFake();
        crud.initData();
    }

    @Test
    public void createAccount() {
        RealAccount account = new RealAccount(55, 1, 1, "ABC123", 44);
        crud.createAccount(account);
        assertEquals(4, crud.getAccounts().size());
    }

    @Test
    public void getAccountById() {
        assertEquals("ABC123", crud.getAccountById(4).getNumber());
    }

    @Test
    public void deleteBankById() {
        crud.deleteBankById(1);
        assertEquals(2, crud.getBanks().size());
    }

    @Test
    public void createMovement() {
        RealMovement movement = new RealMovement(100, 2, 3);
    crud.createMovement(movement);
    long actual = crud.getAccountById(3).getBalance();
    assertEquals(200, actual);
    }

    @Test
    public void updateCustomerName() {

    }
}
