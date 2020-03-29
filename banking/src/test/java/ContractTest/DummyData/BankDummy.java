/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContractTest.DummyData;

import Interfaces.BankInterface;
import contract.DTO.AccountDTO;
import contract.DTO.BankDTO;
import contract.DTO.CustomerDTO;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mikkel
 */
public class BankDummy implements BankInterface{
    Map<String, BankDTO> dummyBanks = new HashMap<>();   
    Map<String, AccountDTO> accounts1 = new HashMap<>();
    Map<String, AccountDTO> accounts2 = new HashMap<>();
    
    Map<String, AccountDTO> CustomerAccounts1 = new HashMap<>();
    Map<String, AccountDTO> CustomerAccounts2 = new HashMap<>();
    
    BankDTO bank1 = new BankDTO("1234567","Danske Bank", accounts1);
    BankDTO bank2 = new BankDTO("7654321","Jydske Bank", accounts2);
    
    CustomerDTO customer1 = new CustomerDTO("10101010", "Mikkel", bank1, CustomerAccounts1);
    CustomerDTO customer2 = new CustomerDTO("10101010", "Mikkel", bank2, CustomerAccounts2);
    
    public BankDummy(){
        
        dummyBanks.put("1", bank1);
        dummyBanks.put("2", bank2);
        
        AccountDTO acc = new AccountDTO(bank1, customer1, "1234", 100, null, null);
        AccountDTO acc2 = new AccountDTO(bank2, customer2, "4321", 200, null, null);
         
        accounts1.put("1",acc);
        accounts2.put("1",acc2);
    
    }

    @Override
    public BankDTO getBank(String id) throws NotFoundException {
        return dummyBanks.get(id);
    }

    @Override
    public Map<String, AccountDTO> getAccounts(String id) throws NotFoundException {
        return dummyBanks.get(id).getAccounts();
       
    }
}
