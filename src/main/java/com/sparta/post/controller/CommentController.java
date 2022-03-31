package com.sparta.post.controller;

import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.models.Comment;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@Controller
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/api/comment")
    public String createComment(@ModelAttribute CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        commentRequestDto.setUsername(userDetails.getUsername());
        commentRequestDto.setUsernickname(userDetails.getUsernickname());
        Comment comment = new Comment(commentRequestDto);


        commentService.createComment(comment);

        return "redirect:/detailPostView?id=" + comment.getPostId();
    }

    @PostMapping("/comment/delete")
    public String deleteComment(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {


        Comment comment = commentService.deleteComment(id,userDetails.getUsername());

        return "redirect:/detailPostView?id=" + comment.getPostId();
    }


    @PostMapping("/comment/update")
    public String updateComment(@RequestParam Long id,@ModelAttribute CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Comment comment = commentService.update(id,commentRequestDto, userDetails.getUsername());

        return "redirect:/detailPostView?id=" + comment.getPostId();
    }


}
