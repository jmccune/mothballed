package org.tierlon.xreffed;


import com.bericotech.fallwizard.FallwizardService;
import com.bericotech.fallwizard.configuration.FallwizardConfiguration;
import com.yammer.dropwizard.config.Bootstrap;

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
}
