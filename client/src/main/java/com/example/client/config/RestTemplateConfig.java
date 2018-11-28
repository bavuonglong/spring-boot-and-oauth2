package com.example.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private OAuth2ClientContext oauth2Context;

    public RestTemplateConfig(OAuth2ClientContext oauth2Context) {
        this.oauth2Context = oauth2Context;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        return new OAuth2RestTemplate(resource(), oauth2Context);
    }

    @ConfigurationProperties(prefix = "security.oauth2")
    private OAuth2ProtectedResourceDetails resource() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
