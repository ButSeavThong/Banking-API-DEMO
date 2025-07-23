package kh.coding.fullstackjpa.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_CUSTOMER = "CUSTOMER ";
    private final String ROLE_STAFF = "STAFF ";
    private final String GENERAL = "GENERAL ";
    private final String ROLE_DEVELOPER = "DEVELOPER ";

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
        http.httpBasic(Customizer.withDefaults());

        // CSRF common protection -> CRRF token
        http.csrf(token->token.disable());

        // stateless
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager configureUsers(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin@123"))
//                .roles(ROLE_ADMIN)
//                .build();
//        manager.createUser(admin);
//
//
//        UserDetails customer = User.builder()
//                .username("customer")
//                .password(passwordEncoder.encode("customer@123"))
//                .roles(ROLE_CUSTOMER)
//                .build();
//        manager.createUser(customer);
//
//        // use bean that we already inject for encrypt code prifix
//        UserDetails staff = User.builder()
//                .username("staff")
//                .password(passwordEncoder.encode("staff@123"))
//                .roles(ROLE_STAFF)
//                .build();
//        manager.createUser(staff);
//
//        UserDetails general = User.builder()
//                .username("general")
//                .password(passwordEncoder.encode("general@123"))
//                .roles(GENERAL)
//                .build();
//        manager.createUser(general);
//
//
//        UserDetails developer = User.builder()
//                .username("developer")
//                .password(passwordEncoder.encode("developer@123"))
//                .roles(ROLE_DEVELOPER)
//                .build();
//        manager.createUser(developer);
//
//        return manager;
//    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

}
