package dk.cphbusiness.banking.DataAccessLayer;

import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealBank;
import dk.cphbusiness.banking.implementations.RealCustomer;
import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;
import dk.cphbusiness.banking.interfaces.Customer;
import dk.cphbusiness.banking.interfaces.Movement;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDOperations implements DataAccessInterface {

    Connector conn = new Connector();

    public CRUDOperations() {

    }

    public void initDB() throws FileNotFoundException {
        Connection con = conn.connect();
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader("D:\\Code\\PBA\\2.Semester\\Test\\2.0\\MockingAndTDD\\banking\\scripts\\initbanking.sql"));
        //Running the script
        sr.runScript(reader);
    }

    public void teardownDB() throws FileNotFoundException {
        Connection con = conn.connect();
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader("D:\\Code\\PBA\\2.Semester\\Test\\2.0\\MockingAndTDD\\banking\\scripts\\teardown.sql"));
        //Running the script
        sr.runScript(reader);

    }

    public void createAccount(Account account) {
        String SQL = "INSERT INTO banktest.account(bankid,customerid,accountNumber, balance) values (?,?,?,?)";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, account.getBank().getId());
            pstmt.setInt(2, account.getCustomer().getId());
            pstmt.setString(3, account.getNumber());
            pstmt.setLong(4, account.getBalance());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Account getAccountById(int id) {
        Account account = null;
        String SQL = "SELECT account.bankid as accbankid,  customerid, \n"
                + "accountNumber, balance, bank.cvr, bank.name as bankname,\n"
                + "customer.cpr, customer.name as customername\n"
                + "FROM banktest.account\n"
                + "INNER JOIN banktest.bank ON banktest.account.bankid = bank.id\n"
                + "INNER JOIN banktest.customer ON banktest.account.customerid = customer.id\n"
                + "where account.id =?";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int bankid = rs.getInt("accbankid");
                String cvr = rs.getString("cvr");
                String bankname = rs.getString("bankname");
                Bank bank = new RealBank(bankid, cvr, bankname);

                String cpr = rs.getString("cpr");
                String customername = rs.getString("customername");
                Customer customer = new RealCustomer(cpr, customername, bank);

                String accountNumber = rs.getString("accountNumber");
                int balance = rs.getInt("balance");
                account = new RealAccount(id, bank, customer, accountNumber, balance);
                System.out.println("return actual account");
            } else {
                System.out.println("return null");
                return account;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return account;
    }
    
        public Account getAccountByNumber(String number) {
        Account account = null;
        String SQL = "SELECT account.id as accountid, account.bankid as accbankid,  customerid, \n"
                + "accountNumber, balance, bank.cvr, bank.name as bankname,\n"
                + "customer.cpr, customer.name as customername\n"
                + "FROM banktest.account\n"
                + "INNER JOIN banktest.bank ON banktest.account.bankid = bank.id\n"
                + "INNER JOIN banktest.customer ON banktest.account.customerid = customer.id\n"
                + "where account.accountNumber =?";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setString(1, number);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int bankid = rs.getInt("accbankid");
                String cvr = rs.getString("cvr");
                String bankname = rs.getString("bankname");
                Bank bank = new RealBank(bankid, cvr, bankname);

                String cpr = rs.getString("cpr");
                String customername = rs.getString("customername");
                Customer customer = new RealCustomer(cpr, customername, bank);

                int id = rs.getInt("accountid"); 
                int balance = rs.getInt("balance");
                account = new RealAccount(id, bank, customer, number, balance);
                System.out.println("return actual account");
            } else {
                System.out.println("return null");
                return account;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return account;
    }
    
    

    public List<Movement> getAccountWithdrawals(int id) {
        List<Movement> result = new ArrayList();
        String SQL = "SELECT amount, targetAccount FROM banktest.movement where sourceAccount =? ";
        try {
            Connection connect = conn.connect();

            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                
                long amount = rs.getInt("amount");
                int target = rs.getInt("targetAccount");
                
                Movement movement = new RealMovement((int) amount, id, target);
                result.add(movement);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
    
       public List<Movement> getAccountDeposists(int id) {
        List<Movement> result = new ArrayList();
        String SQL = "SELECT amount, sourceAccount FROM banktest.movement where targetAccount =? ";
        try {
            Connection connect = conn.connect();

            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                
                long amount = rs.getInt("amount");
                int source = rs.getInt("sourceAccount");
                
                Movement movement = new RealMovement((int) amount, id, source);
                result.add(movement);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    
    

    public void deleteAccountById(int id) {
        String SQL = "DELETE FROM banktest.account WHERE id = ?";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(rs.toString());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBalanceForAccount(int amount, Account account) {
        String SQL = "UPDATE banktest.account SET balance = ? where id = ?";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, amount);
            pstmt.setInt(2, account.getId());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createBank(Bank bank) {
        String SQL = "INSERT INTO banktest.bank(cvr,name) values (?,?)";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setString(1, bank.getCvr());
            pstmt.setString(2, bank.getName());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Bank getBankById(int id) {
        Bank bank = null;
        String SQL = "SELECT id, cvr, name FROM banktest.bank WHERE id = ?";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int bankid = rs.getInt("id");
                String cvr = rs.getString("cvr");
                String name = rs.getString("name");
                bank = new RealBank(bankid, cvr, name);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bank;
    }

    public void deleteBankById(int id) {
        String SQL = "DELETE * FROM banktest.bank WHERE id = ?";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createCustomer(Customer customer) {
        String SQL = "INSERT INTO banktest.customer(cpr,name,bankid) values (?,?,?)";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setString(1, customer.getCpr());
            pstmt.setString(2, customer.getName());
            pstmt.setInt(3, customer.getBank().getId());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Customer getCustomerById(int id) {
        Customer customer = null;
        String SQL = "SELECT customer.id,customer.cpr,customer.name,bank.id as banksownid,bank.cvr,bank.name as banksownname FROM banktest.customer \n"
                + "INNER JOIN banktest.bank on banktest.customer.bankid = banktest.bank.id \n"
                + "WHERE customer.id =?";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String cvr = rs.getString("cvr");
                String bankname = rs.getString("banksownname");
                int bankid = rs.getInt("banksownid");
                Bank bank = new RealBank(bankid, cvr, bankname);
                String cpr = rs.getString("cpr");
                String name = rs.getString("name");
                int customerid = rs.getInt("id");
                customer = new RealCustomer(customerid, cpr, name, bank);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return customer;

    }

    public void updateCustomerName(String name, Customer customer) {
        String SQL = "UPDATE banktest.customer SET name = ? where id = ?";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setString(1, name);
            pstmt.setInt(2, customer.getId());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Movement> getMovementsByAccountId(int id) {
        List<Movement> movements = new ArrayList<>();
        String SQL = "SELECT amount,sourceaccount,targetaccount FROM BankTest.movement where sourceAccount = ? or targetAccount = ?";
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.setInt(2, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                int amount = rs.getInt("amount");
                int source = rs.getInt("sourceaccount");
                int target = rs.getInt("targetaccount");
                Movement movement = new RealMovement(amount, target, source);
                movements.add(movement);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return movements;
    }

    public void createMovement(Movement movement) {
        String SQL = "INSERT INTO banktest.movement(timeOfTransfer, amount, targetAccount, sourceAccount) values (?,?,?,?)";
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        try {
            Connection connect = conn.connect();
            PreparedStatement pstmt = connect.prepareStatement(SQL);

            pstmt.setDate(1, date);
            pstmt.setLong(2, movement.getAmount());
            pstmt.setInt(3, movement.getTargetId());
            pstmt.setInt(4, movement.getSourceId());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*
    public static void main(String[] args) throws FileNotFoundException {
        CRUDOperations crud = new CRUDOperations();
        crud.initDB();
        //crud.teardownDB();
        System.out.println("Running and listening");
    }
    */
}
