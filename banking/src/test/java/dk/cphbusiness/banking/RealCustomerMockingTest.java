package dk.cphbusiness.banking;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RealCustomerMockingTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void customerMustHaveBank(){
        final Bank bank = context.mock(Bank.class);
        final AccountInterface account = context.mock(AccountInterface.class);
        String cpr = "cpr123";
        RealCustomer customer = new RealCustomer(cpr,"Helloblyat");
        context.checking(new Expectations(){{
            oneOf(bank).getCustomer(cpr);
            will(returnValue(customer));
        }});
        assertEquals(customer, bank.getCustomer(cpr));

    }
    @Test
    public void customerMustHaveName(){

    }
}
