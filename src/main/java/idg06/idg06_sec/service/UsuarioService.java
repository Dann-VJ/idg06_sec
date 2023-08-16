/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.service;

import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.model.repositories.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DannVJ
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /*public void registrarUsuario(Usuario usuario) {
        // Aquí puedes agregar más lógica de validación antes de guardar al usuario
        usuarioRepository.save(usuario);
    }*/
    // El encriptador de contraseñas BCryptPasswordEncoder
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registrarUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Establecer el valor predeterminado para el campo "enabled"
        usuario.setEnabled(true); // o false, dependiendo de lo que desees

        // Aquí puedes agregar más lógica de validación antes de guardar al usuario
        usuarioRepository.save(usuario);
    }

    public boolean existeCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo) != null;
    }

    public Usuario findByCorreo(String correoUsuario) {
        return usuarioRepository.findByCorreo(correoUsuario);
    }

    @Transactional
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> findAllUsersExceptPropietario(String correoPropietario) {
        return usuarioRepository.findAllByCorreoNot(correoPropietario);
    }
}
