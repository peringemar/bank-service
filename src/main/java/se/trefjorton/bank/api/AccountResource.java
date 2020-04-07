package se.trefjorton.bank.api;


import se.trefjorton.bank.core.Account;
import se.trefjorton.bank.exceptions.AccountNotFoundException;
import se.trefjorton.bank.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    private AccountService accountService;

    @Inject
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("/{id}")
    public Account getById(@PathParam("id") Long id) {
        return accountService
                .findById(id)
                .orElseThrow(AccountNotFoundException::new);
    }
}