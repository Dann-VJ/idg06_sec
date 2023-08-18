/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package idg06.idg06_sec.model.repositories;

import idg06.idg06_sec.model.entity.Friend;
import idg06.idg06_sec.model.entity.Post;
import idg06.idg06_sec.model.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository; 

/**
 *
 * @author DannVJ
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUsuarioCorreo(String correo);
    
    List<Post> findAllByUsuarioIn(List<Usuario> usuarios); // Cambio aquí
    
    List<Post> findByPermiso(int permiso);
}


