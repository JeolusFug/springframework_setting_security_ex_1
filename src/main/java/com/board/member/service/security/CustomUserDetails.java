// DB에서 읽은 로그인 정보는 UserDetails 인터페이스를 구현하고 있는 객체에 저장되어야 함
// CustomUserDetails는 UserDetails를 구현하고 있음
package com.board.member.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    // 계정의 활성화 여부 (true : 활성화)
    private boolean isEnabled;
    // 계정의 만료 여부 (true : 만료 안됨)
    private boolean isAccountNonExpired;
    // 계정의 잠김 여부 (true : 잠기지 않음)
    private boolean isAccountNonLocked;
    // 계정의 비밀번호 만료 여부 (true : 만료 안됨)
    private boolean isCredentialsNonExpired;
    // 계정의 권한 목록(여러 권한이 있을 수 있음)
    private Collection<? extends GrantedAuthority>authorities;


    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
