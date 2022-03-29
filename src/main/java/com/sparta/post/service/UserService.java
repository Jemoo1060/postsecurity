package com.sparta.post.service;


import com.sparta.post.dto.UserRequestDto;
import com.sparta.post.models.User;
import com.sparta.post.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // 암호화 bean에서 가져옴
    }

    public void registerUser(UserRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        String userNickName = requestDto.getUsernickname();

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();


        User user = new User(username,userNickName, password, email);
        userRepository.save(user);
    }
    
    // 회원 ID 중복확인
    public boolean usernameCheckdup(String username) {
        Optional<User> found = userRepository.findByUsername(username);

        return !found.isPresent();
    }
}
