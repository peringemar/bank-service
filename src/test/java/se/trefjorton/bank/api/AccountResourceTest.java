package se.trefjorton.bank.api;


import io.dropwizard.testing.junit.ResourceTestRule;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Test;
import se.trefjorton.bank.model.Account;
import se.trefjorton.bank.service.AccountService;

import javax.ws.rs.core.Response;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountResourceTest {

    private static final AccountService service = mock(AccountService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AccountResource(service))
            .build();

    @Test
    public void testGetById() {
        when(service.findById(eq(1337L))).thenReturn(new Account(1337L, UUID.randomUUID(), "name"));

        Response response = resources.target("/accounts/1337")
                .request()
                .get();

        assertEquals(HttpStatus.OK_200, response.getStatus());

        Account account = response.readEntity(Account.class);
        assertEquals("name", account.getName());
    }

    @Test
    public void testGetByIdNotFound() {
        when(service.findById(eq(1337L))).thenReturn(null);

        Response response = resources.target("/accounts/1337")
                .request()
                .get();

        assertEquals(HttpStatus.NOT_FOUND_404, response.getStatus());

        verify(service).findById(eq(1337L));
    }
}