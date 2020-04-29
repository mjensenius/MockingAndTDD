
package API;

import com.google.gson.Gson;
import dk.cphbusiness.banking.DataAccessLayer.CRUDOperations;
import dk.cphbusiness.banking.exceptions.NotFoundException;
import dk.cphbusiness.banking.implementations.RealAccount;
import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Account;
import dk.cphbusiness.banking.interfaces.Customer;
import java.io.FileNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    
    
    
    @Path("")
    @GET
    @Produces("text/html")
    public String getHello(@QueryParam("id") int id) throws NotFoundException {
        return "<html><body><div>hello world</div></body></html>";
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
    @Path("customer/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomer(@PathParam("id") int id) throws NotFoundException {
        Customer cust = crudOperation.getCustomerById(id);
        //System.out.println(cust.toString());
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
  
    /**
     * REST endpoint to get account by id
     *
     * @param id
     * @return AccountDetails
     * @throws NotFoundException
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
            @QueryParam("target") String targetAccountNumber
    ) throws NotFoundException {
        long transferamount = Long.parseLong(amount);
        RealAccount acc = (RealAccount) crudOperation.getAccountById(id);
        RealAccount targetAccount = (RealAccount) crudOperation.getAccountByNumber(targetAccountNumber);
        acc.transfer(transferamount, targetAccount);
        crudOperation.createMovement(new RealMovement((int) transferamount, acc.getId(), targetAccount.getId()));
        return Response.status(200)
                .entity("source: " + acc.getNumber() + " --> " + " target: " + targetAccountNumber + " amount --> " + amount)
                .build();
    }
}
