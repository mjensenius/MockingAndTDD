/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContractTest;

import ContractTest.DummyData.CustomerDummy;
import Interfaces.CustomerInterface;
import Interfaces.IHolder;
import contract.DTO.CustomerDTO;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Mikkel
 */
public class ICustomerTest {
    CustomerInterface manager;

    public ICustomerTest() {
        manager = IHolder.customerInterfaceHolder;
        manager = new CustomerDummy();
    }

    /**
     * Test of getCustomer method, of class ICustomerManager.
     */
    @Test
    public void testGetCustomer() throws Exception {
        System.out.println("Customer: getCustomer");
        String id = "1";
        String expResult = "Mikkel";
        CustomerDTO result = manager.getCustomer(id);
        assertEquals(expResult, result.getName());
    }

    /**
     * Test of getAccounts method, of class ICustomerManager.
     */
    @Test
    public void testGetAccounts() throws Exception {
        System.out.println("Customer: getAccounts");
        int expResult = 2;
        assertEquals(expResult, manager.getAccounts("1").size());
    }
}
