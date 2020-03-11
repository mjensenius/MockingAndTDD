/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.banking;

import dk.cphbusiness.banking.exceptions.NotFoundException;
import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealCustomer;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;
import dk.cphbusiness.banking.interfaces.Customer;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.Expectations;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;


public class CustomerMockingTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test(expected = NotFoundException.class)
    public void testCustomerTransferNotFoundException() throws NotFoundException {
        System.out.println("testCustomerTransferNotFoundException");
        final Bank bank = context.mock(Bank.class);

        final String cName = "Mikkel Lindstrøm";
        final String cCpr = "duveddet123";

        Customer c = new RealCustomer(cCpr, cName, bank);

        Account source = new RealAccount(bank, c, "SRC54321");

        c.transfer(10000, source, null);

    }

    @Test
    public void testCustomerTransfer() throws NotFoundException {
        System.out.println("testCustomerTransfer");
        final Bank bank = context.mock(Bank.class);

        final String cName = "Mathias";
        final String cCpr = "12349876";

        Customer c = new RealCustomer(cCpr, cName, bank);

        final String targetNumber = "TGT54321";
        Account source = new RealAccount(bank, c, "SRC54321");
        Account target = new RealAccount(bank, c, targetNumber);

        c.transfer(10000, source, target);

        assertEquals(-10000, source.getBalance());
        assertEquals(10000, target.getBalance());

    }

    @Test(expected = NotFoundException.class)
    public void testGetListOfWithdrawalNotFoundException() throws NotFoundException {
        System.out.println("testGetListOfWithdrawalNotFoundException");
        final Bank bank = context.mock(Bank.class);

        final String cName = "Mathias";
        final String cCpr = "12349876";
        final String accNo = "24";

        Customer c = new RealCustomer(cCpr, cName, bank);

        c.getListOfWithdrawal(accNo);
    }

    @Test
    public void testGetListOfWithdrawal() throws NotFoundException {
        System.out.println("testGetListOfWithdrawal");
        final Bank bank = context.mock(Bank.class);

        final String cName = "Mathias";
        final String cCpr = "12349876";
        final String accNo = "27";


        Customer c = new RealCustomer(cCpr, cName, bank);
        Account acc = new RealAccount(bank, c, accNo);

        acc.withdraw(100);
        acc.withdraw(100);
        c.addAccount(acc);

        assertEquals(c.getListOfWithdrawal(accNo).size(), 2);
    }

    @Test(expected = NotFoundException.class)
    public void testGetListOfDepositNotFoundException() throws NotFoundException {
        System.out.println("testGetListOfDepositNotFoundException");
        final Bank bank = context.mock(Bank.class);

        final String cName = "Mathias";
        final String cCpr = "12349876";;
        final String accNo = "24";

        Customer c = new RealCustomer(cCpr, cName, bank);

        c.getListOfDeposits(accNo);
    }

    @Test
    public void testGetListOfDeposit() throws NotFoundException {
        System.out.println("testGetListOfDeposit");
        final Bank bank = context.mock(Bank.class);

        final String cName = "Mathias";
        final String cCpr = "12349876";
        final String accNo = "24";


        Customer c = new RealCustomer(cCpr, cName, bank);
        Account acc = new RealAccount(bank, c, accNo);

        acc.deposit(100);
        acc.deposit(100);
        c.addAccount(acc);

        assertEquals(c.getListOfDeposits(accNo).size(), 2);
    }

    @Test
    public void testAddAccount() {
        System.out.println("testAddAccount");
        final Bank bank = context.mock(Bank.class);
        final Account account = context.mock(Account.class);

        final String cName = "Mathias";
        final String cCpr = "12349876";
        final String accNo = "24";

        Customer c = new RealCustomer(cCpr, cName, bank);

        context.checking(new Expectations() {
            {

                oneOf(account).getNumber();
                will(returnValue(accNo));
            }
        });
        c.addAccount(account);
        assertEquals(c.getAccounts().size(), 1);
    }

}
