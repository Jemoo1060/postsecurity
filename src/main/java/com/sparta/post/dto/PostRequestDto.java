package com.sparta.post.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestDto {

    private String title;
    private String username;
    private String userNickname;
    private String content;

}
