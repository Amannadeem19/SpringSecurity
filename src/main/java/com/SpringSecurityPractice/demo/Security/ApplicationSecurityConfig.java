package com.SpringSecurityPractice.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.SpringSecurityPractice.demo.Security.ApplicationUserPermission.TEACHER_WRITE;
import static com.SpringSecurityPractice.demo.Security.ApplicationUserRole.Admin;
import static com.SpringSecurityPractice.demo.Security.ApplicationUserRole.Teacher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
                .antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(TEACHER_WRITE.name())
                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(TEACHER_WRITE.name())
                .antMatchers(HttpMethod.PUT,"/api/**").hasAuthority(TEACHER_WRITE.name())
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Teacher.name(), Admin.name())
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
                .authorities(Teacher.getGrantedAuthorities())
                .build();

        UserDetails adminZulfiqar = User.builder()
                .username("zulfiqar")
                .password(passwordEncoder.encode("password"))
                .authorities(Admin.getGrantedAuthorities())
                .build();
                return new InMemoryUserDetailsManager(
                        aman,
                        adminZulfiqar

                );
    }
}
