/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
import dk.cphbusiness.banking.exceptions.NotFoundException;
<<<<<<< HEAD:banking/src/main/java/api/ApiResource.java
import javax.ws.rs.ApplicationPath;
=======
import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Customer;
import java.io.FileNotFoundException;
import javax.ws.rs.Consumes;
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3:banking/src/main/java/API/ApiResource.java
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * REST Web Service
 *
 * @author Mikkel
 */

public class ApiResource extends Application {

    /**
     * REST Web Service
     *
     * @author Mikkel
     */
    CRUDOperations crudOperation = new CRUDOperations();
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
<<<<<<< HEAD:banking/src/main/java/api/ApiResource.java
    }

    @Path("/welcome")
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>WTF IS THIS!!</body></h1></html>";
    }
    
=======
      
    }
    
    
    
    @Path("")
    @GET
    @Produces("text/html")
    public String getHello(@QueryParam("id") int id) throws NotFoundException {
        return "<html><body><div>hello world</div></body></html>";
    }
    
    
    
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3:banking/src/main/java/API/ApiResource.java
    /*
    ---------------------------------
                  CUSTOMER
    ---------------------------------
     */
<<<<<<< HEAD:banking/src/main/java/api/ApiResource.java
    @Path("/customer")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomer(@QueryParam("id") String id) {
        int myid = Integer.parseInt(id);
        return gson.toJson(crudOperation.getCustomerById(myid));
=======
    @Path("customer/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomer(@PathParam("id") int id) throws NotFoundException {
        Customer cust = crudOperation.getCustomerById(id);
        //System.out.println(cust.toString());
        return gson.toJson(crudOperation.getCustomerById(id));
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3:banking/src/main/java/API/ApiResource.java
    }

    /*
    ---------------------------------
                  BANK
    ---------------------------------
     */
<<<<<<< HEAD:banking/src/main/java/api/ApiResource.java
    @Path("/bank")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBank(@QueryParam("id") int id) {
=======
    @Path("bank/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBank(@PathParam("id") int id) throws NotFoundException {
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3:banking/src/main/java/API/ApiResource.java
        return gson.toJson(crudOperation.getBankById(id));
    }

    /*
    ---------------------------------
                  ACCOUNT
    ---------------------------------
     */
<<<<<<< HEAD:banking/src/main/java/api/ApiResource.java

    @Path("/account")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccount(@QueryParam("id") int id) {
        return gson.toJson(crudOperation.getAccountById(id));
    }

    @Path("/account/balance")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBalance(@QueryParam("id") int id) {
        return gson.toJson(crudOperation.getAccountById(id).getBalance());
    }

    @Path("/account/withdrawals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getWithdrawals(@QueryParam("id") int id) {
        return gson.toJson(crudOperation.getAccountById(id).getWithdrawals());
    }

    @Path("/account/deposits")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDeposits(@QueryParam("id") int id) {
        return gson.toJson(crudOperation.getAccountById(id).getDeposits());
    }

    @Path("/account/transfer/id")
=======
    @Path("account/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccount(@PathParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getAccountById(id));
    }

    /**
     * REST endpoint to get account balance by id
     *
     * @param id
     * @return long
     * @throws NotFoundException
     */
    @Path("account/{id}/balance")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBalance(@PathParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getAccountById(id).getBalance());
    }

    /**
     * REST endpoint to get account withdrawals by id
     *
     * @param id
     * @return List of MovementDetails
     * @throws NotFoundException
     */
    @Path("account/{id}/withdrawals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getWithdrawals(@PathParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getAccountWithdrawals(id));
    }

    /**
     * REST endpoint to get account deposits by id
     *
     * @param id
     * @return List of MovementDetails
     * @throws NotFoundException
     */
    @Path("account/{id}/deposits")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDeposits(@PathParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getAccountDeposists(id));
    }

    /**
     * REST endpoint to post (transfer by id) amount from source to target
     *
     * @param amount
     * @param source
     * @param target
     * @return Response.status(200)
     * @throws NotFoundException
     */
    @Path("account/{id}/transfer")
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3:banking/src/main/java/API/ApiResource.java
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transferByAccountId(
<<<<<<< HEAD:banking/src/main/java/api/ApiResource.java
            @QueryParam("id") int id,
            @QueryParam("amount") String amount,
            @QueryParam("source") String sourceAccountNumber,
            @QueryParam("target") String targetAccountNumber
    ) throws NotFoundException {
        long transferamount = Long.parseLong(amount);
        crudOperation.getAccountById(id).transfer(transferamount, targetAccountNumber);
        return Response.status(200)
                .entity("source: " + sourceAccountNumber + " --> " + " target: " + targetAccountNumber + " : " + amount)
                .build();
    }

    @Path("/account/transfer/number")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transferByAccountNumber(@QueryParam("amount") String amount, @QueryParam("source") String sourceAccountNumber,
            @QueryParam("target") String targetAccountNumber) throws NotFoundException {
        long amountToTransfer = Long.parseLong(amount);
        crudOperation.getAccountByNumber(sourceAccountNumber).transfer(amountToTransfer, targetAccountNumber);
        return Response.status(200)
                .entity("source: " + sourceAccountNumber + " --> " + " target: " + targetAccountNumber + " : " + amountToTransfer)
                .build();
    }

    @Path("/account/deposit")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deposit(@QueryParam("amount") String amount, @QueryParam("id") int id) {
        long amountToDeposit = Long.parseLong(amount);
        crudOperation.getAccountById(id).deposit(amountToDeposit);
        return Response.status(200)
                .entity("amount: " + amountToDeposit + " deposited to account with id: " + id)
                .build();
    }

    @Path("/account/withdraw")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response withdraw(
            @QueryParam("amount") String amount,
            @QueryParam("id") int id
    ) throws NotFoundException {
        long amountToWithdraw = Long.parseLong(amount);
        crudOperation.getAccountById(id).deposit(amountToWithdraw);
=======
            @PathParam("id") int id,
            @QueryParam("amount") String amount,
            @QueryParam("target") String targetAccountNumber
    ) throws NotFoundException {
        long transferamount = Long.parseLong(amount);
        RealAccount acc = (RealAccount) crudOperation.getAccountById(id);
        RealAccount targetAccount = (RealAccount) crudOperation.getAccountByNumber(targetAccountNumber);
        acc.transfer(transferamount, targetAccount);
        crudOperation.createMovement(new RealMovement((int) transferamount, acc.getId(), targetAccount.getId()));
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3:banking/src/main/java/API/ApiResource.java
        return Response.status(200)
                .entity("source: " + acc.getNumber() + " --> " + " target: " + targetAccountNumber + " amount --> " + amount)
                .build();
    }
<<<<<<< HEAD:banking/src/main/java/api/ApiResource.java

=======
>>>>>>> 6f0a1ca626ec82dbfd7464c6cc0201521acbd0c3:banking/src/main/java/API/ApiResource.java
}
