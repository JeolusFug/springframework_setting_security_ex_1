package com.board.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCreateForm {
    private Long Id;

    @Size(min = 3, max = 25)
    @NotEmpty(message = "ID를 입력해 주세요.")
    private String memberEmail;

    @NotEmpty(message = "비밀번호를 입력해 주세요.")
    private String memberPassword1;

    @NotEmpty(message = "비밀번호를 확인해 주세요")
    private String memberPassword2;

    @NotEmpty(message = "이름을 입력해 주세요.")
    private String memberName;

    @NotEmpty(message = "나이를 입력해 주세요.")
    private Integer memberAge;

    @NotEmpty(message = "전화번호를 입력해 주세요.")
    private String memberMobile;

}
