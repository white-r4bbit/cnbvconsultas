package com.cnbv.consultas.config;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyVaultConfig {

	@Value("${KVConfig__KVUrl}")
	private String kvUrl;

	@Value("${KVConfig__ClientId}")
	private String clientId;

	@Value("${KVConfig__ClientSecretId}")
	private String clientSecretId;

	@Value("${KVConfig__TenantId}")
	private String tenantId;

	@Bean
	public SecretClient secretClient() {

		ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder().clientId(clientId)
				.clientSecret(clientSecretId).tenantId(tenantId).build();

		return new SecretClientBuilder().vaultUrl(kvUrl).credential(clientSecretCredential).buildClient();
	}
}
