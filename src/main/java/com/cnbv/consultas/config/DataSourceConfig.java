package com.cnbv.consultas.config;

import com.azure.security.keyvault.secrets.SecretClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class DataSourceConfig {

	@Autowired
	private SecretClient secretClient;


	@Bean
	public DataSource dataSource() {
		// Obtén la cadena de conexión y otros secretos necesarios
		
		String connectionString = secretClient.getSecret("CNBV--ConectaProcesos--DbConnection--Java").getValue();

		return DataSourceBuilder.create().url(connectionString)
				.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver").build();
	}
}
