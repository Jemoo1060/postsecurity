package com.sparta.post.service;

import com.sparta.post.models.Comment;
import com.sparta.post.models.Post;
import com.sparta.post.repository.CommentRepository;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@Service // 서비스임을 선언합니다.
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public void update(Long id, PostRequestDto postrequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        if(post.getUsername().equals(postrequestDto.getUsername())){
            post.update(postrequestDto);
        }
    }

    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }


    public void createPost(Post post) {
        postRepository.save(post);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).get();
    }

    @Transactional
    public void deleteOne(Long id, String username) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        if(username.equals(post.getUsername())){
            postRepository.deleteById(id);
            commentRepository.deleteAllByPostId(id);
        }
    }

}
