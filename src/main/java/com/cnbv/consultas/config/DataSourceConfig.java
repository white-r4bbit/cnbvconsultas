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
		
		// String connectionString = secretClient.getSecret("CNBV--ConectaProcesos--DbConnection--Java").getValue();

		String connectionString = "jdbc:sqlserver://172.16.132.77\\SQLDES3CS;databaseName=ConectaProcesosRefactor;integratedSecurity=false;encrypt=true;trustServerCertificate=true;user=idgenConectaProcesos;password=db*VqR_oebv18erbB0";

		return DataSourceBuilder.create().url(connectionString)
				.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver").build();
	}
}
