package com.sparta.post.repository;

import com.sparta.post.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    @Transactional
    void deleteAllByPostId(Long postId);
}
