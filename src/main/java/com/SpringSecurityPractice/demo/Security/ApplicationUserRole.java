package com.SpringSecurityPractice.demo.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.SpringSecurityPractice.demo.Security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    Teacher(Sets.newHashSet(TEACHER_READ, TEACHER_WRITE)),
    Admin(Sets.newHashSet(COURSE_READ, COURSE_WRITE, TEACHER_READ, TEACHER_WRITE));

    private  final Set<ApplicationUserPermission> permissions;
    ApplicationUserRole(Set<ApplicationUserPermission> permissionSets ) {
            this.permissions = permissionSets;
    }
    public Set<ApplicationUserPermission> getPermissions(){
        return this.permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}

