/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import contract.DTO.AccountDTO;
import contract.DTO.MovementDTO;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import java.util.List;

/**
 *
 * @author Mikkel
 */
public interface AccountInterface {

    AccountDTO getAccount(String id) throws NotFoundException;

    void transfer(long amount, int source, int target) throws NotFoundException;

    void transfer(long amount, String sourceAccNumber, String targetAccNumber) throws NotFoundException;

    long getBalance(String id);

    List<MovementDTO> getWithdrawals(String id);

    List<MovementDTO> getDeposits(String id);

    MovementDTO deposit(long amount, String id);

    MovementDTO withdraw(long amount, String id);
}
