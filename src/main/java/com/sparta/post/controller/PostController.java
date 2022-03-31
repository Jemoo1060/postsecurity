package com.sparta.post.controller;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.models.Comment;
import com.sparta.post.models.Post;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.CommentService;
import com.sparta.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    // 글등록 페이지
    @GetMapping("/postView")
    public String postView(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        model.addAttribute("usernickname", userDetails.getUsernickname());

        return "posting";
    }

    // 상세 글 페이지
    @GetMapping("/detailPostView")
    public String detailPostView(@RequestParam Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Post post = postService.getPost(id);
        List<Comment> list= commentService.getComments(id);

        if(userDetails !=  null){
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("usernickname", userDetails.getUsernickname());
        }

        model.addAttribute("post", post);
        model.addAttribute("list", list);


        return "detailPost";
    }
    // 메인페이지 게시판 나열
    @GetMapping("/api/postsInfo")
    @ResponseBody
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // 게시판 등록
    @PostMapping("/api/posts")
    @ResponseBody
    public Post createPost(@RequestBody PostRequestDto postrequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postrequestDto.setUsername(userDetails.getUsername());
        postrequestDto.setUserNickname(userDetails.getUsernickname());
        Post post = new Post(postrequestDto);
        postService.createPost(post);
        return post;
    }

    // 상세글 정보 나열
    @GetMapping("/api/detailposts/{id}")
    @ResponseBody
    public Post getPost(@PathVariable Long id) {

        postService.getPost(id);

        return postService.getPost(id);
    }



    @PostMapping("/updateView")
    public String updateView(@RequestParam Long id,Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        model.addAttribute("usernickname", userDetails.getUsernickname());

        return "postUpdate";
    }


    // 특정 글 삭제
    @PostMapping ("/post/deleteOne")
    public String deleteOne(@RequestParam Long id,  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        postService.deleteOne(id, userDetails.getUsername());

        return "redirect:/";
    }

    @PostMapping("/post/update")
    public String updatePost(@RequestParam  Long id, @ModelAttribute PostRequestDto requestDto) {

        postService.update(id,requestDto);

        return "redirect:/detailPostView?id=" + id;
    }

}

