package org.back.systemklinikimedycznej.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {
            "/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers(WHITE_LIST_URL).permitAll());
        httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        return httpSecurity.build();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(
//            UserDetailsService userDetailsService
//    ){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//
//        return provider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            HttpSecurity httpSecurity,
//            AuthenticationProvider authenticationProvider
//    ) throws Exception {
//        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//                .authenticationProvider(authenticationProvider)
//                .build();
//    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user1")
                .password("user1")
                .roles("DOCTOR").build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public WebSecurityCustomizer customize() {
        return w -> w.ignoring().requestMatchers("/auth/login");
    }
}
