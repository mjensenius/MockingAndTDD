package dk.cphbusiness.banking;

import java.util.ArrayList;
import java.util.List;

public class RealCustomer implements Customer {
    private String cpr;
    private String name;
    private List<Account> account = new ArrayList<>();


    public RealCustomer(String cpr, String name){
        this.cpr = cpr;
        this.name = name;
    }
}
