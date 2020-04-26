package ContractTest;

import ContractTest.DummyData.AccountDummy;
import ContractTest.DummyData.BankDummy;
import ContractTest.DummyData.CustomerDummy;
import Interfaces.IHolder;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Mikkel
 */

@RunWith (Suite.class)
@Suite.SuiteClasses({IAccountTest.class, ICustomerTest.class, IBankTest.class})
public class Beforeclass {
 
    @BeforeClass
    public static void beforeClass() {
        IHolder.accountInterfaceHolder = new AccountDummy();
        IHolder.bankInterfaceHolder = new BankDummy();
        IHolder.customerInterfaceHolder = new CustomerDummy();
    }
    
}
