
package APITest;


import api.rest.ApiResource;
import com.google.gson.Gson;
import contract.DTO.AccountDTO;
import contract.DTO.BankDTO;
import contract.DTO.CustomerDTO;
import contract.DTO.MovementDTO;
import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Alexander W. Hørsted-Andersen <awha86@gmail.com>
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiResourceTest {

    Gson gson = new Gson();
    CRUDOperations crud;
    ApiResource apiResource = new ApiResource();

    @Before
    public  void before() throws IOException {
         crud = new CRUDOperations();
         crud.initDB();;
    }
    

    @Test
    public void testGetCustomer() throws Exception {
        System.out.println("getCustomer");
        int id = 1;
        String expName = "Mogens";
        CustomerDTO result = gson.fromJson(apiResource.getCustomer(id), CustomerDTO.class);
        assertEquals(expName, result.getName());
    }

    @Test
    public void testGetAccount() throws Exception {
        System.out.println("getAccount");
        int id = 1;
        String accNumber = "ABC123";
        AccountDTO resultAccount= gson.fromJson(apiResource.getAccount(id), AccountDTO.class);
        assertEquals(accNumber, resultAccount.getNumber());
    }

    @Test
    public void testGetBank() throws Exception {
        System.out.println("getBank");
        int id = 1;
        String expResult = "Jyske Bank";
        BankDTO result = gson.fromJson(apiResource.getBank(id), BankDTO.class);
        assertEquals(expResult, result.getName());
    }


    @Test
    public void testGetBalance() throws Exception {
        System.out.println("getBalance");
        int id = 1;
        long expBalance = 100;
        long result = gson.fromJson(apiResource.getBalance(id), long.class);
        assertEquals(expBalance, result);
    }


    @Test
    public void testGetWithdrawals() throws Exception {
        System.out.println("getWithdrawals");
        int id = 1;
        int expNumberOfWithdrawals = 1;
        MovementDTO[] resultMovements = gson.fromJson(apiResource.getWithdrawals(id), MovementDTO[].class);
        List<MovementDTO> ListOfWithdrawals = Arrays.asList(resultMovements);
        assertEquals(expNumberOfWithdrawals, ListOfWithdrawals.size());
    }

    @Test
    public void testGetDeposits() throws Exception {
        System.out.println("getDeposits");
        int id = 2;
        int expNumberOfDeposits = 1;
        MovementDTO[] resultMovements = gson.fromJson(apiResource.getDeposits(id), MovementDTO[].class);
        List<MovementDTO> ListOfDeposits = Arrays.asList(resultMovements);
        assertEquals(expNumberOfDeposits, ListOfDeposits.size());
    }

}