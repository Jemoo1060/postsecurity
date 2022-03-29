package com.sparta.post.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {
    private String username;
    private String usernickname;
    private String password;
    private String email;
}
