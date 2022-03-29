package com.sparta.post.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.post.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Post extends Timestamped{

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String username;


    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String usernickname;


    @Column(nullable = false)
    private String content;

    // 생성 시 이용합니다.
    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.usernickname = requestDto.getUserNickname();
        this.content = requestDto.getContent();
    }

    // update 시 이용합니다.
    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.usernickname = requestDto.getUserNickname();
        this.content = requestDto.getContent();
    }


}
