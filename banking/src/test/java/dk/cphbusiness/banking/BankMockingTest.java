package dk.cphbusiness.banking;

import dk.cphbusiness.banking.exceptions.NotFoundException;
import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealCustomer;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;

import java.util.HashMap;

import dk.cphbusiness.banking.implementations.RealBank;
import dk.cphbusiness.banking.interfaces.Customer;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;

public class BankMockingTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test(expected = NotFoundException.class)
    public void testGetAccountNotFoundException() throws NotFoundException {
        System.out.println("testGetAccountNotFoundException");

        final String targetNumber = "TGT54321";
        final String cvr = "23456789";
        final String name = "BankBank";
        final Bank bank = new RealBank(cvr, name, new HashMap<>());

        bank.getAccount(targetNumber);
    }

    @Test
    public void testGetAccount() throws NotFoundException {
        final Customer customer = context.mock(Customer.class);

        final String targetNumber = "TGT54321";
        final String cvr = "23456789";
        final String name = "BankBank";
        final Bank bank = new RealBank(cvr, name, new HashMap<>());

        final Account acc = new RealAccount(bank, customer, targetNumber);

        context.checking(new Expectations() {
            {
                oneOf(customer).addAccount(acc);
            }
        });

        bank.addAccount(acc);
        Account accountRes = bank.getAccount(targetNumber);
        assertEquals(acc, accountRes);
    }

    @Test
    public void testGetAccounts() {
        final String cvr = "23456789";
        final String name = "BankBank";
        final Bank bank = new RealBank(cvr, name, new HashMap<>());

        final String cpr = "23456768";
        final String cName = "jeff";
        final Customer customer = new RealCustomer(cpr, cName, bank);

        final String accNumber = "1";
        final String accNumberSecond = "2";

        bank.addAccount(new RealAccount(bank, customer, accNumber));
        bank.addAccount(new RealAccount(bank, customer, accNumberSecond));

        assertEquals(customer.getAccounts().size(), 2);
    }

}