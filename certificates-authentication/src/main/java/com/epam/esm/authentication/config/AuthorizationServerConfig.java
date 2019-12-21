package com.epam.esm.authentication.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(AuthorizationProperties.class)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";

    private AuthorizationProperties authorizationProperties;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder encoder;

    public AuthorizationServerConfig(AuthenticationManager authenticationManager, AuthorizationProperties authorizationProperties, PasswordEncoder encoder){
        this.authenticationManager = authenticationManager;
        this.authorizationProperties = authorizationProperties;
        this.encoder = encoder;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(authorizationProperties.privateKey);
        converter.setVerifierKey(authorizationProperties.publicKey);
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient(authorizationProperties.clientId)
                .secret(encoder.encode("password"))
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, REFRESH_TOKEN)
                .scopes(SCOPE_READ, SCOPE_WRITE);
    }
}


