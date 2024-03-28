package com.board.member.service.security;

public class UserRoleEntity {
    private String loginUserId;
    private String roleName;

    public UserRoleEntity(String loginUserId, String roleName) {
        this.loginUserId = loginUserId;
        this.roleName = roleName;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String userLoginId) {
        this.loginUserId = userLoginId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
