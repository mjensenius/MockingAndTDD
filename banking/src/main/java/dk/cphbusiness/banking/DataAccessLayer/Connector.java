package dk.cphbusiness.banking.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private String url = "jdbc:postgresql://127.0.0.1:5432/BankTest";
    private String user = "postgres";
    private String password = "Stilldre1";
    private Properties props = new Properties();

    public Connector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("ssl","true");
    }
    
    public Connector (){
        this.url = this.url;
        this.user = this.user;
        this.password = this.password;
        props.setProperty("user", this.user);
        props.setProperty("password", this.password);
        props.setProperty("ssl","true");
    }

    public Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(this.url, this.props);
            System.out.println("Connected to the PostgreSQL server successfully.");
             return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

