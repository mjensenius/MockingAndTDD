package dk.cphbusiness.banking.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connector {
    private String DRIVER = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/banktest?autoReconnect=true&useSSL=false";
    private String user = "";
    private String password = "";

    public Connector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

    }
    
    public Connector (){
        this.url = this.url;
        this.user = this.user;
        this.password = this.password;
    }

    public Connection connect()  {
        Connection conn = null;
        try {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Connected to the MySQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
}

