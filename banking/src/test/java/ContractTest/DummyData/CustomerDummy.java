/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContractTest.DummyData;

import Interfaces.CustomerInterface;
import contract.DTO.AccountDTO;
import contract.DTO.BankDTO;
import contract.DTO.CustomerDTO;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import java.util.HashMap;
import java.util.Map;

public class CustomerDummy implements CustomerInterface {
       
    Map<String, AccountDTO> CustomerAccounts1 = new HashMap();
    Map<String, AccountDTO> CustomerAccounts2 = new HashMap();
    
    Map<String, AccountDTO> BankAccounts1 = new HashMap();
    Map<String, AccountDTO> BankAccounts2 = new HashMap();
       
    BankDTO bank1 = new BankDTO("123456","Danske Bank", BankAccounts1 );
    BankDTO bank2 = new BankDTO("654321","Jydske Bank", BankAccounts2 );
    
    
     Map<String, CustomerDTO> dummyCustomers = new HashMap<>();     
     Map<String, AccountDTO> accounts = new HashMap<>();
     
     public CustomerDummy(){
         
         CustomerDTO Mikkel = new CustomerDTO("101010110", "Mikkel", bank1, CustomerAccounts1);
         CustomerDTO Mathias = new CustomerDTO("20202020", "Mathias", bank2, CustomerAccounts2);
                 
         dummyCustomers.put("1", Mikkel);
         dummyCustomers.put("2", Mathias);
 
         AccountDTO acc = new AccountDTO(Mikkel.getBank(), Mikkel, "1234", 1000, null, null);
         AccountDTO acc1 = new AccountDTO(Mikkel.getBank(), Mikkel, "12345", 100000, null, null);
         AccountDTO acc2 = new AccountDTO(Mathias.getBank(), Mathias, "1234", 500, null, null);
         
         CustomerAccounts1.put("1", acc);
         CustomerAccounts1.put("2", acc1);

         CustomerAccounts2.put("1", acc2);
         
         accounts.put("1",acc);
         accounts.put("2",acc2);
      
     }

    @Override
    public CustomerDTO getCustomer(String id) throws NotFoundException {
        return dummyCustomers.get(id);
    }

    @Override
    public Map<String, AccountDTO> getAccounts(String id) throws NotFoundException {
        return dummyCustomers.get(id).getAccounts();
    }
}
