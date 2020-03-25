package dk.cphbusiness.banking.DataAccessLayer;

import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.interfaces.Account;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

public class CRUDOperations {
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
        String SQL = "SELECT accountid, bankid, customerid, accountNumber, balance FROM Account WHERE id = ?;";
        try (Connection connect = conn.connect();
             PreparedStatement pstmt = connect.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int accountid = rs.getInt("accountid");
                int bankid = rs.getInt("bankid");
                int customerid = rs.getInt("customerid");
                String accountNumber = rs.getString("accountNumber");
                int balance = rs.getInt("balance");
                account = new RealAccount(accountid,bankid,customerid,accountNumber,balance);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return account;
    }
    public void initDB() throws FileNotFoundException {
       Connection con =  conn.connect();
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        Reader reader = new BufferedReader(new FileReader("/Users/sofie/Desktop/Software Development/MockingAndTDD/banking/scripts/initbanking.sql"));
        //Running the script
        sr.runScript(reader);
    }
    public void teardownDB() throws FileNotFoundException {
        Connection con =  conn.connect();
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
