package com.sparta.post.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp="^[A-Za-z[0-9]]{2,50}$",
            message = "아이디는 영문자와 숫자 3자이상의 아이디여야 합니다.")
    private String username;

    @Column(nullable = false)
    private String usernickname;


    @Column(nullable = false)
    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{3,100}",
            message = "영문,숫자,특수문자 포함 4자이상의 비밀번호여야 합니다.")
    private String password;

    @Column
    private String email;


    public User(String username, String usernickname,String password, String email) {
        this.username = username;
        this.usernickname = usernickname;
        this.password = password;
        this.email = email;

    }

}
