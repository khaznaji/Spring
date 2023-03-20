package com.example.intermove.Repositories.forum;

import com.example.intermove.Entities.forum.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.post.postID = :postId")
    List<Comment> findAllByPostId(@Param("postId") Long postId);

}
