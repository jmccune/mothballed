package org.tierlon.xreffed;


import com.bericotech.fallwizard.FallwizardService;
import com.bericotech.fallwizard.configuration.FallwizardConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by justinanddiana on 6/24/14.
 */
public class XReffedService extends FallwizardService<FallwizardConfiguration> {

    public static void main(String[] args) throws Exception {
        new XReffedService().run(args);
    }

    public void initialize(Bootstrap<FallwizardConfiguration> xrefServiceConfig) {
        super.initialize(xrefServiceConfig);

    }

    @Override
    public void run(FallwizardConfiguration configuration, Environment environment) throws Exception {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter("allowedOrigins", "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowedMethods", "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter("preflightMaxAge", "5184000"); // 2 months
        filter.setInitParameter("allowCredentials", "true");

        super.run(configuration, environment);
    }
}
