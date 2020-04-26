/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContractTest;

import ContractTest.DummyData.AccountDummy;
import Interfaces.AccountInterface;
import Interfaces.IHolder;
import contract.DTO.AccountDTO;
import contract.DTO.MovementDTO;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Mikkel
 */
public class IAccountTest {
    AccountInterface manager;

    public IAccountTest() {
        //manager = IHolder.accountInterfaceHolder;
        manager = new AccountDummy();
    }

    /**
     * Test of getAccount method.
     */
    @Test
    public void testGetAccount() throws Exception {
        System.out.println("Account: getAccount");
        String id = "1";
        String expectedAccountNo = "ABC123";
        AccountDTO result = manager.getAccount(id);

        assertEquals(result.getClass(), AccountDTO.class);
        assertEquals(expectedAccountNo, result.getNumber());
    }

     /**
     * Test of getBalance method, of class IAccountManager.
     */
    @Test
    public void testGetBalance() {
        System.out.println("Account: getBalance");
        String id = "3";
        long expectedBalance = 5000;
        long balance123 = manager.getBalance(id);
        assertEquals(expectedBalance, manager.getBalance(id));
    }
    
    /**
     * Test of transfer method, of class IAccountManager.
     */
    @Test
    public void testTransferWithAccountNumber() throws Exception {
        System.out.println("Account: transferWithAccount");
        String first = "1";
        String second = "2";
        long amount = 100L;
        long expectedBalanceFirst = 900;
        long expectedBalanaceSecond = 600;
        String sourceAccNumber = "ABC123"; //dumbledore
        String targetAccNumber = "1234"; //voldemort
        manager.transfer(amount, sourceAccNumber, targetAccNumber);
        assertEquals(expectedBalanceFirst, manager.getBalance(first));
        assertEquals(expectedBalanaceSecond, manager.getBalance(second));
    }
    


    /**
     * Test of getWithdrawals method, of class IAccountManager.
     */
    @Test
    public void testGetWithdrawals() {
        System.out.println("Account: getWithdrawals");
        String id = "2";
        int expectedNumberOfWidthdrawals = 2;
        List<MovementDTO> result = manager.getWithdrawals(id);
        assertEquals(expectedNumberOfWidthdrawals, result.size());
    }

    /**
     * Test of getDeposits method, of class IAccountManager.
     */
    @Test
    public void testGetDeposits() {
        System.out.println("Account: getDeposits");
        String id = "1";
        int expectedNumberOfWidthdrawals = 1;
        List<MovementDTO> result = manager.getDeposits(id);
        assertEquals(expectedNumberOfWidthdrawals, result.size());
    }

    /**
     * Test of deposit method, of class IAccountManager.
     */
    @Test
    public void testDeposit() throws NotFoundException {
        System.out.println("Account: deposit");
        String id = "1";
        long amount = 1000;
        long expectedBalance = manager.getBalance(id) + amount;
        LocalDateTime date = LocalDateTime.now();
        MovementDTO expResult = new MovementDTO(date, 1000);
        MovementDTO result = manager.deposit(amount, id);
        AccountDTO account = manager.getAccount(id);
        
        assertEquals(expectedBalance, account.getBalance());
        assertEquals(result.getClass(), expResult.getClass());
    }

    /**
     * Test of withdraw method, of class IAccountManager.
     */
    @Test
    public void testWithdraw() throws NotFoundException {
        System.out.println("Account: withdraw");
        String id = "1";
        long amount = 1000;
        long expectedBalance = manager.getBalance(id) - amount;
        LocalDateTime date = LocalDateTime.now();
        MovementDTO expResult = new MovementDTO(date, 1000);
        MovementDTO result = manager.withdraw(amount, id);
        AccountDTO ad = manager.getAccount(id);
        
        assertEquals(expectedBalance, ad.getBalance());
        assertEquals(result.getClass(), expResult.getClass());
    }
}
