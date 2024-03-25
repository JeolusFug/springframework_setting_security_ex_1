package com.board.user;

import com.board.user.UserDTO;
import com.board.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO create(Long id, String memberEmail, String memberPassword, String memberName, Integer memberAge, String memberMobile){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setMemberEmail(memberEmail);
        userDTO.setMemberPassword(passwordEncoder.encode(memberPassword));
        userDTO.setMemberName(memberName);
        userDTO.setMemberAge(memberAge);
        userDTO.setMemberMobile(memberMobile);
        this.userRepository.save(userDTO);
        return userDTO;
    }
}
