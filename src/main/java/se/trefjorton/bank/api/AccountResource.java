package se.trefjorton.bank.api;


import io.dropwizard.hibernate.UnitOfWork;
import org.eclipse.jetty.http.HttpStatus;
import se.trefjorton.bank.model.Account;
import se.trefjorton.bank.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    private final AccountService accountService;

    @Inject
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork(readOnly = true)
    public Account getById(@PathParam("id") Long id) {
        Account account = accountService.findById(id);

        if (account != null) {
            return account;
        } else {
            throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
        }
    }
}