
package ContractTest.DummyData;
import Interfaces.AccountInterface;
import contract.DTO.AccountDTO;
import contract.DTO.BankDTO;
import contract.DTO.CustomerDTO;
import contract.DTO.MovementDTO;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDummy implements AccountInterface {

    Map<String, AccountDTO> dummyAccounts = new HashMap<>();
    LocalDateTime now = LocalDateTime.now();
    Map<String, AccountDTO> BankAccounts1 = new HashMap();
    Map<String, AccountDTO> BankAccounts2 = new HashMap();
    Map<String, AccountDTO> CustomerAccounts1 = new HashMap();
    Map<String, AccountDTO> CustomerAccounts2 = new HashMap();
       
    BankDTO bank1 = new BankDTO("123456","Danske Bank", BankAccounts1 );
    BankDTO bank2 = new BankDTO("654321","Jydske Bank", BankAccounts2 );
    
    CustomerDTO customer1 = new CustomerDTO("10101010", "Mikkel", bank1, CustomerAccounts1);
    CustomerDTO customer2 = new CustomerDTO("20202020", "Mathias", bank2, CustomerAccounts2);
    
    
    
    public AccountDummy() {
        AccountDTO Mikkel = new AccountDTO(bank1, customer1, "ABC123", 1000,
                new ArrayList<MovementDTO>() {
            {
                add(new MovementDTO(now, 100));
            }
        },
                new ArrayList<MovementDTO>() {
            {
                add(new MovementDTO(now, 400));
            }
        }
        );
        
        AccountDTO mathias = new AccountDTO(bank2, customer2, "1234", 500,
                new ArrayList<MovementDTO>() {
            {
                add(new MovementDTO(now, 400));
                add(new MovementDTO(now, 700));
            }
        },
                new ArrayList<MovementDTO>() {
            {
                add(new MovementDTO(now, 1000));
                add(new MovementDTO(now, 1200));
            }
        }
        );
        
        AccountDTO Vorestredjemand = new AccountDTO(bank2, customer2, "123456789", 5000,null,null);

        dummyAccounts.put("1", Mikkel);
        dummyAccounts.put("2", mathias);
        dummyAccounts.put("3", Vorestredjemand);
        
    }

    @Override
    public AccountDTO getAccount(String id) throws NotFoundException {
        return dummyAccounts.get(id);
    }

    @Override
    public void transfer(long amount, int source, int target) throws NotFoundException {
        this.withdraw(amount, Integer.toString(source));
        this.deposit(amount, Integer.toString(target));
    }

    @Override
    public void transfer(long amount, String sourceAccNumber, String targetAccNumber) throws NotFoundException {
        String source = "";
        String target = "";
        
        for(Map.Entry<String, AccountDTO> entry : dummyAccounts.entrySet()) {
            AccountDTO value = entry.getValue();
            if (value.getNumber().equals(sourceAccNumber)) 
                source = entry.getKey();
            if (value.getNumber().equals(targetAccNumber))
                target = entry.getKey();
        }
        
        this.withdraw(amount, source);
        this.deposit(amount, target);
    }

    @Override
    public long getBalance(String id) {
        return dummyAccounts.get(id).getBalance();
    }

    @Override
    public List<MovementDTO> getWithdrawals(String id) {
        return dummyAccounts.get(id).getWithdrawals();
    }

    @Override
    public List<MovementDTO> getDeposits(String id) {
        return dummyAccounts.get(id).getDeposits();
    }

    @Override
    public MovementDTO deposit(long amount, String id) {
        dummyAccounts.get(id).setBalance((int) (dummyAccounts.get(id).getBalance() + amount));
        MovementDTO md = new MovementDTO(now, amount);
        dummyAccounts.get(id).getWithdrawals().add(md);
        return md;
    }
    
    @Override
    public MovementDTO withdraw(long amount, String id) {
        dummyAccounts.get(id).setBalance(dummyAccounts.get(id).getBalance()- (int)amount);
        MovementDTO md = new MovementDTO(now, -amount);
        dummyAccounts.get(id).getWithdrawals().add(md);
        return md;
    }
}
