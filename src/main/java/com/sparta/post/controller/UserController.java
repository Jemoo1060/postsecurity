package com.sparta.post.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.post.dto.UserRequestDto;
import com.sparta.post.service.KakaoUserService;
import com.sparta.post.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/join")
    public String signup() {
        return "join";
    }

    // 아이디 중복 확인
    @ResponseBody
    @PostMapping("/user/check_dup")
    public boolean usernameCheckdup(@RequestParam String username) {

        return userService.usernameCheckdup(username);
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/join")
    public String registerUser(UserRequestDto requestDto) {

        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }


    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {

        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);

        return "redirect:/";
    }
}

