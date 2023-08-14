/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.service;

import idg06.idg06_sec.model.entity.Comment;
import idg06.idg06_sec.model.entity.Post;
import idg06.idg06_sec.model.repositories.CommentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DannVJ
 */
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
    
    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findByPost(post);
    }
}
