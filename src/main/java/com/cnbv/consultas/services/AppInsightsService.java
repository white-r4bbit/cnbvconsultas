package com.cnbv.consultas.services;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.TelemetryConfiguration;
import org.springframework.stereotype.Service;

@Service
public class AppInsightsService {

	private final TelemetryClient telemetryClient;


	public AppInsightsService(TelemetryConfiguration telemetryConfiguration) {
		this.telemetryClient = new TelemetryClient(telemetryConfiguration);
	}

	public void trackEvent(String name) {
		// // properties.put("ClientIPAddress", clientIPAddress);
		telemetryClient.trackEvent(name);
	}

	public void trackException(Exception ex) {
		// // Map<String, String> properties = new HashMap<>();
		// // properties.put("ClientIPAddress", clientIPAddress); // Agregar la IP como
		// propiedad personalizada
		telemetryClient.trackException(ex);
	}
}