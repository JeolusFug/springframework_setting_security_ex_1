// CustomUserDetailsService는 UserDetailsService 인터페이스를 구현함
// 사용자가 로그인을 할 때 아이디를 입력하면 해당 아이디를 loadUserByUsername() 메소드의 인자로 전달
// 해당 아이디에 해당하는 정보가 없으면 UsernameNotFoundException이 발생
// 정보가 있을 경우에는 UserDetails 이너페이스를 구현한 객체를 리턴
package com.board.member.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    // CustomUserDbService는 인터페이스
    // 해당 인터페이스를 구현하고 있는 객체(MemberServiceImpl)가 Bean으로 등록되어 있어야 함
    @Autowired
    UserDbService userDbService;
    
    // UserDetailsService 인터페이스는 loadUserByUsername 1개의 메소드만 선언
    // 사용자가 로그인을 할 때 아이디를 입력하면 해당 아이디를 loadUserByUsername() 메소드의 인자로 전달
    // 해당 아이디에 해당하는 정보가 없으면 UsernameNotFoundException이 발생
    // 정보가 있을 경우엔 UserDetails 인터페이스를구현한 객체를 리턴
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        // loginId에 해당하는 정보를 DB에서 읽어 CustomUser 객체에 저장
        // 해당 정보를 CustomUserDetails 객체에 저장
        UserEntity customUser = userDbService.getUser(loginId);

        if (customUser == null) {
            throw new UsernameNotFoundException("사용자가 입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");
        }

        // CustomUserDetails을 만들고 userDbService에서 값을 가져와 넣어주고 리턴하면서 파라미터의 아이디와 비교하기 때문에
        // 고정된 값(MemberServiceImpl에서 설정해둔 carami)을 나오게 해도
        // 결국 파라미터와 다른 값이기 때문에 Security의 검증 과정에 걸리는 듯 함
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUsername(customUser.getLoginUserId());
        customUserDetails.setPassword(customUser.getPassword());

        List<UserRoleEntity> customRoles = userDbService.getUserRoles(loginId);

        // 로그인 한 사용자의 권한 정보를 GrantedAuthority를 구현하고 있는 SimpleGrantedAuthority 객체에 담아 리스트에 추가
        // MemberRole(권한자) 이름은 "ROLE_"로 시작되어야 함
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (customRoles != null) {
            for (UserRoleEntity customRole : customRoles) {
                authorities.add(new SimpleGrantedAuthority(customRole.getRoleName()));
            }
        }

        // CustomUserDetails 객체에 권한 목록(authorities)를 설정
        customUserDetails.setAuthorities(authorities);
        customUserDetails.setEnabled(true);
        customUserDetails.setAccountNonExpired(true);
        customUserDetails.setAccountNonLocked(true);
        customUserDetails.setCredentialsNonExpired(true);

        return customUserDetails;
    }
}
