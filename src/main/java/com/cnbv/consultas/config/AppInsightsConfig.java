package com.cnbv.consultas.config;

import com.azure.security.keyvault.secrets.SecretClient;
import com.microsoft.applicationinsights.TelemetryConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInsightsConfig {

	@Autowired
	private SecretClient secretClient;

	@Bean
	public TelemetryConfiguration telemetryConfiguration() {
		TelemetryConfiguration configuration = new TelemetryConfiguration();
		String instrumentationKey = secretClient.getSecret("CNBV--PortalGestion--ApplInsightsKey").getValue();
		configuration.setInstrumentationKey(instrumentationKey);
		return configuration;
	}

}