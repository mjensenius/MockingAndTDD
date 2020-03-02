package dk.cphbusiness.banking;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountMockingTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Test
  public void testAccountTransfer() {
    final Customer customer = context.mock(Customer.class);
    final Bank bank = context.mock(Bank.class);
    final String targetNumber = "TGT54321";
    Account source = new Account(bank, customer, "SRC54321");
    Account target = new Account(bank, customer, targetNumber);
    context.checking(new Expectations(){{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
      //oneOf(bank).getName();
      }});
    source.transfer(10000, targetNumber);
    assertEquals(-10000, source.getBalance());
    assertEquals(10000, target.getBalance());
    //context.assertIsSatisfied();
    }

  @Test
  public void depositsMustBeSaved() {
    final Customer customer = context.mock(Customer.class);
    final Bank bank = context.mock(Bank.class);
    final String targetNumber = "TGT54321";
    Account source = new Account(bank, customer, "SRC54321");
    Account target = new Account(bank, customer, targetNumber);
    context.checking(new Expectations() {{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
    }});
    source.transfer(10000, "TGT54321");
    assertEquals(1, source.getDeposits().size());
    // context.assertIsSatisfied();
  }
  @Test
  public void depositAmountMustMatchTransferAmount(){
    final Customer customer = context.mock(Customer.class);
    final Bank bank = context.mock(Bank.class);
    final String targetNumber = "TGT54321";
    Account source = new Account(bank, customer, "SRC54321");
    Account target = new Account(bank, customer, targetNumber);
    context.checking(new Expectations() {{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
    }});
    source.transfer(10000, "TGT54321");
    assertEquals(10000, source.getDeposits().get(0).getAmount());
    // context.assertIsSatisfied();
  }
  @Test
  public void depositTargetMustMatchTransferTarget(){
    final Customer customer = context.mock(Customer.class);
    final Bank bank = context.mock(Bank.class);
    final String targetNumber = "TGT54321";
    Account source = new Account(bank, customer, "SRC54321");
    Account target = new Account(bank, customer, targetNumber);
    context.checking(new Expectations() {{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
    }});
    source.transfer(10000, "TGT54321");
    assertEquals(target, source.getDeposits().get(0).getTarget());
    // context.assertIsSatisfied();
  }
  @Test
  public void withdrawalMustBeSaved() {
    final Customer customer = context.mock(Customer.class);
    final Bank bank = context.mock(Bank.class);
    final String targetNumber = "TGT54321";
    Account source = new Account(bank, customer, "SRC54321");
    Account target = new Account(bank, customer, targetNumber);
    context.checking(new Expectations() {{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
    }});
    source.transfer(10000, "TGT54321");
    assertEquals(1, source.getWithdrawals().size());
    // context.assertIsSatisfied();
  }
  @Test
  public void withdrawalAmountMustMatchTransferAmount(){
    final Customer customer = context.mock(Customer.class);
    final Bank bank = context.mock(Bank.class);
    final String targetNumber = "TGT54321";
    Account source = new Account(bank, customer, "SRC54321");
    Account target = new Account(bank, customer, targetNumber);
    context.checking(new Expectations() {{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
    }});
    source.transfer(10000, "TGT54321");
    assertEquals(10000, source.getWithdrawals().get(0).getAmount());
    // context.assertIsSatisfied();
  }
  @Test
  public void withdrawalTargetMustMatchTransferTarget(){
    final Customer customer = context.mock(Customer.class);
    final Bank bank = context.mock(Bank.class);
    final String targetNumber = "TGT54321";
    Account source = new Account(bank, customer, "SRC54321");
    Account target = new Account(bank, customer, targetNumber);
    context.checking(new Expectations() {{
      oneOf(bank).getAccount(targetNumber);
      will(returnValue(target));
    }});
    source.transfer(10000, "TGT54321");
    assertEquals(source, source.getWithdrawals().get(0).getTarget());
    // context.assertIsSatisfied();
  }
  }



