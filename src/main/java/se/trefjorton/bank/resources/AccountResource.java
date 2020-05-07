package se.trefjorton.bank.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import org.eclipse.jetty.http.HttpStatus;
import se.trefjorton.bank.model.Account;
import se.trefjorton.bank.service.AccountService;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/accounts", tags = "Accounts", produces = MediaType.APPLICATION_JSON)
public class AccountResource {
    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork(readOnly = true)
    @ApiOperation(value = "Retrieve an account by id.")
    public Account getById(@PathParam("id") Long id) {
        Account account = accountService.findById(id);

        if (account != null) {
            return account;
        } else {
            throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
        }
    }
}