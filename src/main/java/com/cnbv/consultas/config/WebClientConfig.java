package com.cnbv.consultas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	@Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                        .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                            .secure(sslContextSpec -> sslContextSpec.sslContext(SslContextBuilder
                                .forClient()
                                .trustManager(InsecureTrustManagerFactory.INSTANCE)))));
    }
}
