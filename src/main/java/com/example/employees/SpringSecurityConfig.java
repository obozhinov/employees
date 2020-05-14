package com.example.employees;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("USER","ADMIN")
                .and()
                .withUser("user").password("{noop}password").roles("USER");

    }


    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employee/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/employee").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/employee/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/employee/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

//    public UserDetailsService userDetailsService() {
//        //ok for demo
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("user").password("password").roles("USER").build());
//        manager.createUser(users.username("admin").password("password").roles("ADMIN").build());
//        return manager;
//    }

}
