package kh.coding.fullstackjpa.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class KCConfigsecurity {
    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_CUSTOMER = "CUSTOMER ";
    private final String ROLE_STAFF = "STAFF ";

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // TODO
        // 1. make all endpoints secured
        http.authorizeHttpRequests(endpoints ->
                endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/customers/**")
                        .hasAnyRole(ROLE_ADMIN, ROLE_STAFF)

                        .requestMatchers(HttpMethod.PUT, "/api/v1/customers/**")
                        .hasAnyRole(ROLE_ADMIN, ROLE_STAFF)

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/**")
                        .hasRole(ROLE_ADMIN)

                        .requestMatchers(HttpMethod.GET, "/api/v1/customers/**")
                        .hasAnyRole(ROLE_STAFF, ROLE_ADMIN, ROLE_CUSTOMER)

                        .anyRequest()
                        .authenticated());

        // 2. disable form ligin of web
        http.formLogin(formLogin -> formLogin.disable());

        // set security mechanism = HTTP basic authentiaction =>  JWT, OAUTH2

        http.oauth2ResourceServer(auth->auth.jwt(Customizer.withDefaults()));

        // CSRF common protection -> CRRF token
        http.csrf(token->token.disable());

        // stateless
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
            Collection<String> roles = realmAccess.get("roles");

            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());
        };

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtConverter;
    }




}
