package dk.cphbusiness.banking.DataAccessLayer;

import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealBank;
import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Bank;
import dk.cphbusiness.banking.interfaces.Customer;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CRUDOperations implements DataAccessInterface {
    Connector conn = new Connector("jdbc:postgresql://localhost:5432/Banking", "postgres", "m4th145bj");

    public void createAccount(Account account) {
        String SQL = "INSERT INTO Account(bankid,customerid,accountNumber, balance) values (?,?,?,?)";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {

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
        String SQL = "SELECT accountid, bankid, customerid, accountNumber, balance FROM Account WHERE id = ?";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int accountid = rs.getInt("accountid");
                int bankid = rs.getInt("bankid");
                int customerid = rs.getInt("customerid");
                String accountNumber = rs.getString("accountNumber");
                int balance = rs.getInt("balance");
                account = new RealAccount(accountid, bankid, customerid, accountNumber, balance);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return account;
    }

    public void deleteAccountById(int id) {
        String SQL = "DELETE * FROM Account WHERE id = ?";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBalanceForAccount(int amount, Account account) {
        String SQL = "UPDATE Account SET balance = ? where id = ?";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {
            pstmt.setInt(1, amount);
            pstmt.setInt(2, account.getId());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createBank(Bank bank) {
        String SQL = "INSERT INTO Bank(cvr,name) values (?,?)";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {
            pstmt.setString(1, bank.getCvr());
            pstmt.setString(2, bank.getName());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Bank getBankById(int id) {
        Bank bank = null;
        String SQL = "SELECT id, cvr, name FROM bank WHERE id = ?";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {
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
        String SQL = "DELETE * FROM Bank WHERE id = ?";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createCustomer(Customer customer){
        String SQL = "INSERT INTO Customer(cpr,name,bankid) values (?,?,?)";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {
            pstmt.setString(1, customer.getCpr());
            pstmt.setString(2, customer.getName());
            pstmt.setInt(3, customer.getBank().getId());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void updateCustomerName(String name, Customer customer){
        String SQL = "UPDATE Customer SET name = ? where id = ?";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, customer.getId());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createMovement(RealMovement movement){
        String SQL = "INSERT INTO Movement(timeOfTransfer, amount, targetAccount, sourceAccount) values (?,?,?,?)";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {

            pstmt.setDate(1, (java.sql.Date) new Date(System.currentTimeMillis()));
            pstmt.setLong(2, movement.getAmount());
            pstmt.setInt(3, movement.getTargetId());
            pstmt.setInt(4, movement.getSourceId());
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void initDB() throws FileNotFoundException {
        Connection con = conn.connect();
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader("/Users/sofie/Desktop/Software Development/MockingAndTDD/banking/scripts/initbanking.sql"));
        //Running the script
        sr.runScript(reader);
    }

    public void teardownDB() throws FileNotFoundException {
        Connection con = conn.connect();
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader("/Users/sofie/Desktop/Software Development/MockingAndTDD/banking/scripts/teardown.sql"));
        //Running the script
        sr.runScript(reader);

    }

    public static void main(String[] args) throws FileNotFoundException {
        CRUDOperations crud = new CRUDOperations();
        crud.initDB();
        crud.teardownDB();
    }
}
