package com.sparta.post.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.post.dto.UserRequestDto;
import com.sparta.post.models.User;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.KakaoUserService;
import com.sparta.post.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if(userDetails != null){
            model.addAttribute("usernickname", userDetails.getUsernickname());
        }

        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/join")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if(userDetails != null){
            model.addAttribute("usernickname", userDetails.getUsernickname());
        }

        return "join";
    }

    // 아이디 중복 확인
    @ResponseBody
    @PostMapping("/user/check_dup")
    public boolean usernameCheckdup(@RequestParam String username) {

        return userService.usernameCheckdup(username);
    }

    // 서버에서 정규식 표현 유효성 사용하기
    @PostMapping("/user/sendSignUpEmail")
    @ResponseBody
    public Map<String, String> sendSignUpEmail(@ModelAttribute @Valid User user, BindingResult errors) {

        Map<String, String> validatorResult = new HashMap<>();
        validatorResult.put("check","true");

        if (errors.hasErrors()) {
            validatorResult = userService.validateHandling(errors,  validatorResult);

            validatorResult.put("check","false");
            return validatorResult;
        }


        if(user.getPassword().contains(String.valueOf(user.getUsername()))){
            validatorResult.put("valid_idContainPw", "비밀번호에 아이디가 포함되어있습니다.");
            validatorResult.put("check","false");
            return validatorResult;
        }

        return validatorResult;
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


