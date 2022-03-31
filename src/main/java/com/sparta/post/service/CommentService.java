package com.sparta.post.service;


import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.models.Comment;
import com.sparta.post.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@Service // 서비스임을 선언합니다.
public class CommentService {

    private final CommentRepository commentRepository;


    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getComments(Long id) {
        return commentRepository.findByPostId(id);
    }

    public Comment deleteComment(Long id, String username) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("없는 comment 입니다."));

        if(comment.getUsername().equals(username)){
            commentRepository.delete(comment);
        }

        return comment;
    }

    @Transactional
    public Comment update(Long id, CommentRequestDto commentRequestDto, String username) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("없는 comment 입니다."));


        if(commentRequestDto.getUsername().equals(username)){
            System.out.println(commentRequestDto.toString());
            comment.update(commentRequestDto);
        }

        return comment;
    }
}
