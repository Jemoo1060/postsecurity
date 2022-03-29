package com.sparta.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long postId;
    private String username;
    private String usernickname;
    private String content;


    @Override
    public String toString() {
        return "CommentRequestDto{" +
                "postId=" + postId +
                ", username='" + username + '\'' +
                ", userNickname='" + usernickname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
