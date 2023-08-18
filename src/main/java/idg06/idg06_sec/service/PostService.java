/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.service;

import idg06.idg06_sec.model.entity.Friend;
import idg06.idg06_sec.model.entity.Post;
import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.model.repositories.PostRepository;
import idg06.idg06_sec.model.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    /*
    public List<Post> findPostsByPermisoAndAmigos(String correoUsuario) {
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
        List<Friend> amigos = usuario.getAmigos();

        List<Post> publicaciones = postRepository.findAllByUsuarioIn(amigos);

        List<Post> publicacionesFiltradas = new ArrayList<>();
        for (Post publicacion : publicaciones) {
            if (publicacion.getPermiso() == 0 || publicacion.getPermiso() == 1) {
                publicacionesFiltradas.add(publicacion);
                System.out.println("Publicación encontrada: " + publicacion);
            }
        }
        
        System.out.println("Todas las publicaciones: "+publicacionesFiltradas);
        return publicacionesFiltradas;
    }
    public List<Post> findPostsByPermisoAndAmigos(String correoUsuario) {
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
        List<Friend> amigos = usuario.getAmigos();
        
        System.out.println("Amigos: " + amigos);

        List<Post> publicacionesAmigos = postRepository.findAllByUsuarioIn(amigos);
        List<Post> publicacionesPublicas = postRepository.findByPermiso(0);
        
        System.out.println("Publicaciones de amigos: " + publicacionesAmigos);
        System.out.println("Publicaciones publicas: " + publicacionesPublicas);

        List<Post> publicacionesFiltradas = new ArrayList<>();
        publicacionesFiltradas.addAll(publicacionesAmigos);
        publicacionesFiltradas.addAll(publicacionesPublicas);
        
        System.out.println("Publicaciones Filtradas: " + publicacionesFiltradas);
 
        return publicacionesFiltradas;
    }*/
    public List<Post> findPostsByPermisoAndAmigos(String correoUsuario) {
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario);
        List<Friend> amigos = usuario.getAmigos();
        System.out.println("Amigooos........." + amigos);

        // Crear una lista de usuarios amigos basada en las relaciones en 'amigos'
        List<Usuario> amigosUsuarios = amigos.stream()
                .map(Friend::getFriend) // Obtenemos la referencia al amigo de la relación
                .collect(Collectors.toList());

        // Agregar el usuario actual a la lista de amigosUsuarios
        amigosUsuarios.add(usuario);

        // Obtener publicaciones de amigos y públicas
        List<Post> publicacionesAmigos = postRepository.findAllByUsuarioIn(amigosUsuarios);
        List<Post> publicacionesPublicas = postRepository.findByPermiso(0);

        System.out.println("Publicaciones de amigos: " + publicacionesAmigos);
        System.out.println("Publicaciones publicas: " + publicacionesPublicas);

        // Filtrar publicaciones para mostrar
        List<Post> publicacionesFiltradas = new ArrayList<>();

        // Agregar todas las publicaciones de amigos
        publicacionesFiltradas.addAll(publicacionesAmigos);

        // Agregar todas las publicaciones públicas si el usuario no es el creador
        for (Post publicacion : publicacionesPublicas) {
            if (!publicacion.getUsuario().equals(usuario) && !publicacionesFiltradas.contains(publicacion)) {
                publicacionesFiltradas.add(publicacion);
            }
        }

        System.out.println("--Publicaciones Filtradas-- " + publicacionesFiltradas);

        return publicacionesFiltradas;
    }

}
