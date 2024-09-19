package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService securityCustomUserDetailService;

    //configuration of authentication provider  for spring security
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //user details service ka object
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);
        //password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        httpSecurity.formLogin(formLogin -> {
            //login page ka url
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
           // formLogin.failureForwardUrl("/login?error=true");
            formLogin.successForwardUrl("/user/dashboard");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });

        httpSecurity.csrf(csrf -> csrf.disable());

        httpSecurity.logout(logout
                -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
        );

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
