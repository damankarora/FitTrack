package com.activitytracker.foodlogger.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/user").permitAll()
                .antMatchers("/**").authenticated()
                .and().formLogin().and().httpBasic();

        http.csrf().disable();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails details = User.withDefaultPasswordEncoder().username("admin").password("1234").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(details);
    }
}