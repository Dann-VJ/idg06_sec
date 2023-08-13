/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.service;

import idg06.idg06_sec.model.entity.Post;
import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.model.repositories.PostRepository;
import idg06.idg06_sec.model.repositories.UsuarioRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DannVJ
 */
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PostService(PostRepository postRepository, UsuarioRepository usuarioRepository) {
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> findByUsuarioCorreo(String correo) {
        return postRepository.findByUsuarioCorreo(correo);
    }

    public Post createPost(Post post, String correoUsuario) {
        // Obtener el usuario por correo
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
        if (usuario != null) {
            post.setUsuario(usuario);
            post.setFecha(new Date()); // Establecer la fecha actual
            return postRepository.save(post);
        } else {
            // Manejo de error si el usuario no existe
            // Puedes lanzar una excepción o manejarlo de otra manera
            return null;
        }
    }

    // Otros métodos relacionados con la funcionalidad de los posts
}
