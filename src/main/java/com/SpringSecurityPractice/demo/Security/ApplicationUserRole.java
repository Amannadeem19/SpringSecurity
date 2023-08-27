package com.SpringSecurityPractice.demo.Security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.SpringSecurityPractice.demo.Security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    Teacher(Sets.newHashSet(TEACHER_READ, TEACHER_WRITE)),
    Admin(Sets.newHashSet(COURSE_READ, COURSE_WRITE));

    private  final Set<ApplicationUserPermission> permissions;
    ApplicationUserRole(Set<ApplicationUserPermission> permissionSets ) {
            this.permissions = permissionSets;
    }
    public Set<ApplicationUserPermission> getPermissions(){
        return this.permissions;
    }
}

