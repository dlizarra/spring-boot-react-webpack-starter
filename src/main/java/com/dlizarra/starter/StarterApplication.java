package com.dlizarra.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

public class StarterApplication extends SpringApplication {

	public StarterApplication(final Class<?> clazz) {
		super(clazz);
	}

	@Override
	protected void configureProfiles(final ConfigurableEnvironment environment, final String[] args) {
		super.configureProfiles(environment, args);

		final boolean standaloneActive = environment.acceptsProfiles(StarterProfiles.STANDALONE);
		final boolean stagingActive = environment.acceptsProfiles(StarterProfiles.STAGING);
		final boolean productionActive = environment.acceptsProfiles(StarterProfiles.PRODUCTION);

		if (stagingActive && productionActive) {
			throw new IllegalStateException("Cannot activate staging and production profiles at the same time.");
		} else if (productionActive || stagingActive) {
			System.out.println("Activating " +
					(productionActive ? StarterProfiles.PRODUCTION : StarterProfiles.STAGING) + " profile.");
		} else if (standaloneActive) {
			System.out.println("Activating " +
					"the default standalone profile.");
		} else {
			throw new IllegalStateException(
					"Unknown profiles specified. Please specify one of default, staging or production.");
		}
	}

}
