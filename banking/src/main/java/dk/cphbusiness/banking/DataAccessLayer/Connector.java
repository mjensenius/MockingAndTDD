package dk.cphbusiness.banking.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
<<<<<<< HEAD
import java.util.Properties;

public class Connector {
    private String url = "jdbc:postgresql://127.0.0.1:5432/BankTest";
    private String user = "postgres";
    private String password = "Stilldre1";
    private Properties props = new Properties();
=======
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/banktest?autoReconnect=true&useSSL=false";
    private String user = "root";
    private String password = "Stilldre1!";
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3

    public Connector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("ssl","true");
    }
    
    public Connector (){
<<<<<<< HEAD
        this.url = this.url;
        this.user = this.user;
        this.password = this.password;
        props.setProperty("user", this.user);
        props.setProperty("password", this.password);
        props.setProperty("ssl","true");
=======
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3
    }

    public Connection connect() {
        try {
<<<<<<< HEAD
            Connection conn = DriverManager.getConnection(this.url, this.props);
            System.out.println("Connected to the PostgreSQL server successfully.");
             return conn;
=======
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Connected to the MySQL server successfully.");
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

