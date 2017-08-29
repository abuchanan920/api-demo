package com.thoughtworks.demo;

import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_HEADERS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_METHODS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_ORIGINS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOW_CREDENTIALS_PARAM;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.thoughtworks.demo.resources.GreetingResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class APIApplication extends Application<APIConfiguration> {
	public static void main(String[] args) throws Exception {
        new APIApplication().run(args);
    }

	@Override
    public String getName() {
        return "demo";
    }

    @Override
    public void initialize(Bootstrap<APIConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<APIConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(APIConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }
    
	@Override
	public void run(APIConfiguration configuration, Environment environment) throws Exception {
		final GreetingResource greetingResource = new GreetingResource(
				configuration.getGreeting(), configuration.getPerson());
		environment.jersey().register(greetingResource);

		enableCORS(environment);
	}

	private void enableCORS(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORSFilter", CrossOriginFilter.class);
        
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, environment.getApplicationContext().getContextPath() + "*");
        filter.setInitParameter(ALLOWED_METHODS_PARAM, "GET,OPTIONS");
        filter.setInitParameter(ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(ALLOWED_HEADERS_PARAM, "Origin, Content-Type, Accept, Authorization");
        filter.setInitParameter(ALLOW_CREDENTIALS_PARAM, "true");
	}}
