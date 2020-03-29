/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContractTest;

import Interfaces.BankInterface;
import Interfaces.IHolder;
import contract.DTO.BankDTO;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Mikkel
 */
public class IBankTest {
       BankInterface manager;

    public IBankTest() {
       manager = IHolder.bankInterfaceHolder;
    }

    /**
     * Test of getBank method, of class IBankManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetBank() throws Exception {
        System.out.println("Bank: getBank");
        String id = "1";
        String expResult = "Danske Bank";
        BankDTO result = manager.getBank(id);
        assertEquals(expResult, result.getName());
    }

    /**
     * Test of getAccounts method, of class IBankManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAccounts() throws Exception {
       System.out.println("Bank: getAccounts");
       int expResult = 1;  
       assertEquals(expResult, manager.getAccounts("1").size());

    } 
}
