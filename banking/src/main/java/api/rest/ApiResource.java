/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.rest;

import com.google.gson.Gson;
import contract.DTO.AccountDTO;
import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Customer;
import dk.cphbusiness.banking.interfaces.Movement;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Mikkel
 */
@Path("")
public class ApiResource {

    CRUDOperations crudOperation = new CRUDOperations();
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
    }

    @Path("welcome")
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
    @Path("customer/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomer(@PathParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getCustomerById(id));
    }

    /*
    ---------------------------------
                  BANK
    ---------------------------------
     */
    @Path("bank/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBank(@PathParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getBankById(id));
    }

    /*
    ---------------------------------
                  ACCOUNT
    ---------------------------------
     */
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
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transferByAccountId(
            @PathParam("id") int id,
            @QueryParam("amount") String amount,
            @QueryParam("target") String targetId
    ) throws NotFoundException {
        long transferamount = Long.parseLong(amount);
        int targetID = Integer.parseInt(targetId);
        RealAccount acc = (RealAccount) crudOperation.getAccountById(id);
        RealAccount accTarget = (RealAccount) crudOperation.getAccountById(targetID);
        acc.transfer(transferamount, accTarget);
        crudOperation.createMovement(new RealMovement((int) transferamount, targetID, id));
        crudOperation.createMovement(new RealMovement((int) transferamount, id, targetID));
        return Response.status(200)
                .entity("source: " + acc.getNumber() + " --> " + " target: " + accTarget.getNumber() + " amount: " + transferamount)
                .build();
    }

    @Path("/account/transfer/number")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transferByAccountNumber(
            @QueryParam("amount") String amount,
            @QueryParam("source") String sourceAccountNumber,
            @QueryParam("target") String targetAccountNumber) throws NotFoundException {
        long amountToTransfer = Long.parseLong(amount);
        
        RealAccount acc = (RealAccount) crudOperation.getAccountByNumber(sourceAccountNumber);
        RealAccount accTarget = (RealAccount) crudOperation.getAccountByNumber(targetAccountNumber);
        //acc.transfer(amountToTransfer, accTarget);
        crudOperation.createMovement(new RealMovement((int) amountToTransfer, acc.getId(), accTarget.getId()));
        crudOperation.createMovement(new RealMovement((int) amountToTransfer, accTarget.getId(), acc.getId()));
        return Response.status(200)
                .entity("source: " + sourceAccountNumber + " --> " + " target: " + targetAccountNumber + " amount: " + amountToTransfer)
                .build();
    }

    @Path("/account/{id}/deposit")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deposit(
            @PathParam("id") int id,
            @QueryParam("amount") String amount
    ) {
        long amountToDeposit = Long.parseLong(amount);
        crudOperation.getAccountById(id).deposit(amountToDeposit);
        return Response.status(200)
                .entity("amount: " + amountToDeposit + " deposited to account with id: " + id)
                .build();
    }

}
