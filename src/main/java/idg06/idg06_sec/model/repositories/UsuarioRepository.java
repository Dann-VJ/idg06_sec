/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package idg06.idg06_sec.model.repositories;

import idg06.idg06_sec.model.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author DannVJ
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByCorreo(String correo);
    
    List<Usuario> findAllByCorreoNot(String correoPropietario);
}
