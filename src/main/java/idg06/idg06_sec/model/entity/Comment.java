/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author DannVJ
 */
@Entity
@Data
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Post post;
    
     // Getter and Setter for 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for 'text'
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Getter and Setter for 'usuario'
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Getter and Setter for 'post'
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
