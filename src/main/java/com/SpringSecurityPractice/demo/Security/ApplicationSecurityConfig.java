package com.SpringSecurityPractice.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.SpringSecurityPractice.demo.Security.ApplicationUserRole.Admin;
import static com.SpringSecurityPractice.demo.Security.ApplicationUserRole.Teacher;

@Configuration
@EnableWebSecurity

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
   private final PasswordEncoder passwordEncoder;


   @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasAnyRole(Admin.name(), Teacher.name())
                .antMatchers("/course/api/**").hasRole(Admin.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails aman = User.builder()
                .username("aman")
                .password(passwordEncoder.encode("aman"))
                .roles(Teacher.name()) // ROLE_TEACHER
                .build();

        UserDetails adminZulfiqar = User.builder()
                .username("zulfiqar")
                .password(passwordEncoder.encode("password"))
                .roles(Admin.name(), Teacher.name()) // ROLE_ADMIN, ROLE_TEACHER
                .build();
                return new InMemoryUserDetailsManager(
                        aman,
                        adminZulfiqar

                );
    }
}
