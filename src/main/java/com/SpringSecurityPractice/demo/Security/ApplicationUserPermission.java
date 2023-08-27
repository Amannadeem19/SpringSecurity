package com.SpringSecurityPractice.demo.Security;

public enum ApplicationUserPermission {
        TEACHER_READ("teacher:read"),
        TEACHER_WRITE("teacher:write"),
        COURSE_READ("course:read"),
        COURSE_WRITE("course:write");

        private  final String permission;
    ApplicationUserPermission(String permission) {
    this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
