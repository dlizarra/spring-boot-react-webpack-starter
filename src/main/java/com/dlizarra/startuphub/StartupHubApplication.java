package com.dlizarra.startuphub;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

public class StartupHubApplication extends SpringApplication {

	public StartupHubApplication(final Class<?> clazz) {
		super(clazz);
	}

	@Override
	protected void configureProfiles(final ConfigurableEnvironment environment, final String[] args) {
		super.configureProfiles(environment, args);

		final boolean standaloneActive = environment.acceptsProfiles(StartupHubProfiles.STANDALONE);
		final boolean stagingActive = environment.acceptsProfiles(StartupHubProfiles.STAGING);
		final boolean productionActive = environment.acceptsProfiles(StartupHubProfiles.PRODUCTION);

		if (stagingActive && productionActive) {
			throw new IllegalStateException("Cannot activate staging and production profiles at the same time.");
		} else if (productionActive || stagingActive) {
			System.out.println("Activating " +
					(productionActive ? StartupHubProfiles.PRODUCTION : StartupHubProfiles.STAGING) + " profile.");
		} else if (standaloneActive) {
			System.out.println("Activating " +
					"the default standalone profile.");
		} else {
			throw new IllegalStateException(
					"Unknown profiles specified. Please specify one of default, staging or production.");
		}
	}

}
