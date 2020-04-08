package se.trefjorton.bank;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import se.trefjorton.bank.api.AccountResource;
import se.trefjorton.bank.db.dao.AccountDAO;
import se.trefjorton.bank.db.entities.AccountEntity;
import se.trefjorton.bank.health.DummyHealthCheck;
import se.trefjorton.bank.service.AccountService;

public class DwApplication extends Application<DwConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DwApplication().run("server", "config.yml");
    }

    @Override
    public String getName() {
        return "bank-service";
    }

    @Override
    public void initialize(Bootstrap<DwConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        bootstrap.addBundle(hibernate);

        super.initialize(bootstrap);
    }

    @Override
    public void run(DwConfiguration configuration,
                    Environment environment) {
        AccountDAO accountDAO = new AccountDAO(hibernate.getSessionFactory());
        AccountService accountService = new AccountService(accountDAO);
        AccountResource accountResource = new AccountResource(accountService);

        environment
                .jersey()
                .register(accountResource);

        environment
                .healthChecks()
                .register("dummy", new DummyHealthCheck());
    }

    private final HibernateBundle<DwConfiguration> hibernate = new HibernateBundle<>(AccountEntity.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DwConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
}
