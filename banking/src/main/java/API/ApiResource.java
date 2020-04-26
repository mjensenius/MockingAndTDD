
package API;

import com.google.gson.Gson;
import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("")
public class ApiResource {

    CRUDOperations crudOperation = new CRUDOperations();
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    public ApiResource() {
        
    }
    /*
    ---------------------------------
                  CUSTOMER
    ---------------------------------
    */
    /**
     * REST endpoint to get customer by id
     *
     * @param id
     * @return CustomerDetails
     * @throws NotFoundException
     */
    @Path("customer")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomer(@QueryParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getCustomerById(id));
    }

 
    /*
    ---------------------------------
                  BANK
    ---------------------------------
    */
    /**
     * REST endpoint to get bank by id
     *
     * @param id
     * @return BankDetails
     * @throws NotFoundException
     */
    @Path("bank")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBank(@QueryParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getBankById(id));
    }


    /*
    ---------------------------------
                  ACCOUNT
    ---------------------------------
    */
  
    /**
     * REST endpoint to get account by id
     *
     * @param id
     * @return AccountDetails
     * @throws NotFoundException
     */
    @Path("account")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAccount(@QueryParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getAccountById(id));
    }

    /**
     * REST endpoint to get account balance by id
     *
     * @param id
     * @return long
     * @throws NotFoundException
     */
    @Path("account/balance")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBalance(@QueryParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getAccountById(id).getBalance());
    }

    /**
     * REST endpoint to get account withdrawals by id
     *
     * @param id
     * @return List of MovementDetails
     * @throws NotFoundException
     */
    @Path("account/withdrawals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getWithdrawals(@QueryParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getAccountById(id).getWithdrawals());
    }

    /**
     * REST endpoint to get account deposits by id
     *
     * @param id
     * @return List of MovementDetails
     * @throws NotFoundException
     */
    @Path("account/deposits")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDeposits(@QueryParam("id") int id) throws NotFoundException {
        return gson.toJson(crudOperation.getAccountById(id).getDeposits());
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
    @Path("account/transfer/id")
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

    /**
     * REST endpoint to post (transfer by number) amount from sourceAccNumber to
     * targetAccNumber
     *
     * @param amount
     * @param sourceAccNumber
     * @param targetAccNumber
     * @return Response.status(200)
     * @throws NotFoundException
     */
    @Path("account/transfer/number")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transferByAccountNumber(
            @QueryParam("amount") String amount,
            @QueryParam("source") String sourceAccountNumber,
            @QueryParam("target") String targetAccountNumber
    ) throws NotFoundException {
        long amountToTransfer = Long.parseLong(amount);
        crudOperation.getAccountByNumber(sourceAccountNumber).transfer(amountToTransfer, targetAccountNumber);
        return Response.status(200)
                .entity("source: " + sourceAccountNumber + " --> " + " target: " + targetAccountNumber + " : " + amountToTransfer)
                .build();
    }

    /**
     * REST endpoint to deposit amount to account with id
     *
     * @param amount
     * @param id
     * @return MovementDetails
     * @throws NotFoundException
     */
    @Path("account/deposit")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deposit(
            @QueryParam("amount") String amount,
            @QueryParam("id") int id
    ) throws NotFoundException {
        long amountToDeposit= Long.parseLong(amount);
        crudOperation.getAccountById(id).deposit(amountToDeposit);
        return Response.status(200)
                .entity("amount: " + amountToDeposit + " deposited to account with id: " + id)
                .build();
    }

    /**
     * REST endpoint to withdraw amount from account with id
     *
     * @param amount
     * @param id
     * @return MovementDetails
     * @throws NotFoundException
     */
    @Path("account/withdraw")
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