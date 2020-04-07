package se.trefjorton.bank;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import se.trefjorton.bank.api.AccountResource;
import se.trefjorton.bank.db.entities.AccountEntity;
import se.trefjorton.bank.db.repositories.AccountRepository;
import se.trefjorton.bank.health.DummyHealthCheck;
import se.trefjorton.bank.service.AccountService;

import java.util.Collections;
import java.util.List;

public class BankApplication extends Application<BankConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BankApplication().run("server", "config.yml");
    }

    @Override
    public String getName() {
        return "bank-service";
    }

    @Override
    public void initialize(Bootstrap<BankConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

    @Override
    public void run(BankConfiguration configuration,
                    Environment environment) {
        AccountRepository accountRepository = new AccountRepository(initAccounts());
        AccountService accountService = new AccountService(accountRepository);
        AccountResource accountResource = new AccountResource(accountService);

        environment
                .jersey()
                .register(accountResource);

        environment
                .healthChecks()
                .register("dummy", new DummyHealthCheck());
    }


    private List<AccountEntity> initAccounts() {
        return Collections.singletonList(new AccountEntity(1337L, "Perry"));
    }
}
