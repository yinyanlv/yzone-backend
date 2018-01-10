package com.yzone.enums;

public enum UserRole {

    NORMAL(0, "普通用户"), ADMIN(1, "管理员"), SUPER_ADMIN(2, "超级管理员");

    private int roleCode;
    private String roleName;

    UserRole(int code, String name) {
        this.roleCode = code;
        this.roleName = name;
    }

    public int getRoleCode() {

        return roleCode;
    }

    public String getRoleName() {

        return roleName;
    }
}
