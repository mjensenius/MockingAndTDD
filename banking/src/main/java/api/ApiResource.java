/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    }

    @Path("/welcome")
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>WTF IS THIS!!</body></h1></html>";
    }
    
    /*
    ---------------------------------
                  CUSTOMER
    ---------------------------------
     */
    @Path("/customer")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomer(@QueryParam("id") String id) {
        int myid = Integer.parseInt(id);
        return gson.toJson(crudOperation.getCustomerById(myid));
    }

    /*
    ---------------------------------
                  BANK
    ---------------------------------
     */
    @Path("/bank")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBank(@QueryParam("id") int id) {
        return gson.toJson(crudOperation.getBankById(id));
    }

    /*
    ---------------------------------
                  ACCOUNT
    ---------------------------------
     */

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
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transferByAccountId(
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
        return Response.status(200)
                .entity("amount withdrawed: " + amountToWithdraw + " from: " + id)
                .build();
    }

}
