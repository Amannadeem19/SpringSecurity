package com.SpringSecurityPractice.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.SpringSecurityPractice.demo.Security.ApplicationUserRole.Teacher;

@Repository("fake")
public class FakeApplicationUserDAOService implements ApplicationUserDAO{
        private final PasswordEncoder passwordEncoder;
        @Autowired
    public FakeApplicationUserDAOService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return  getApplicationUsers()
                .stream()
                .filter(applicationUser ->  username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    public List<ApplicationUser> getApplicationUsers(){
            List<ApplicationUser> applicationUsers = Lists.newArrayList(
                    new ApplicationUser(
                            "aman",
                            passwordEncoder.encode("aman"),
                            Teacher.getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true

                    ),
                    new ApplicationUser(
                            "nadeem",
                            passwordEncoder.encode("nadeem"),
                            Teacher.getGrantedAuthorities(),
                            true,
                            true,
                            true,
                            true

                    )
            );
            return applicationUsers;
    }
}
