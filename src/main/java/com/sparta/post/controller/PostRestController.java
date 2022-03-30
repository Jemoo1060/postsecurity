package com.sparta.post.controller;



import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.models.Comment;
import com.sparta.post.models.Post;
import com.sparta.post.models.User;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.PostService;
import com.sparta.post.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostRestController {

    private final PostService postService;

    // 메인페이지 게시판 나열
    @GetMapping("/api/postsInfo")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // 게시판 등록
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto postrequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postrequestDto.setUsername(userDetails.getUsername());
        postrequestDto.setUserNickname(userDetails.getUserNickname());
        Post post = new Post(postrequestDto);
        postService.createPost(post);
        return post;
    }

    // 상세글 정보 나열
    @GetMapping("/api/detailposts/{id}")
    public Post getPost(@PathVariable Long id) {

        postService.getPost(id);

        return postService.getPost(id);
    }






}
