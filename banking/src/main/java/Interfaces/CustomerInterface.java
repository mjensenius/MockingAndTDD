/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import contract.DTO.AccountDTO;
import contract.DTO.CustomerDTO;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mikkel
 */
public interface CustomerInterface {

    CustomerDTO getCustomer(String id) throws NotFoundException;

    Map<String, AccountDTO> getAccounts(String id) throws NotFoundException;
    
}
