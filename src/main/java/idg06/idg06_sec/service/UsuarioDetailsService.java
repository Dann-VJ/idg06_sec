/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.service;

import idg06.idg06_sec.model.entity.Rol;
import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.model.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DannVJ
 */
@Service("userDetailsService")
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Correo electrónico recibido: " + username);
        Usuario user = repo.findByCorreo(username);
        if (user == null) {
            throw new UsernameNotFoundException("Correo no encontrado: " + username);
        }
        
        // Imprimir la contraseña almacenada en la consola
        System.out.println("Contraseña almacenada en la base de datos: " + user.getPassword());

        /*
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Rol rol : user.getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
            System.out.println(rol.getAuthority());
        }*/
        
        // Obtener los roles del usuario y convertirlos a GrantedAuthority
        List<GrantedAuthority> authorities = user.getAuthorities()
                .stream()
                .map(rol -> {
                    System.out.println("Rol: " + rol.getAuthority());
                    return new SimpleGrantedAuthority(rol.getAuthority());
                })
                .collect(Collectors.toList());
        
        
        System.out.println("Usuario encontrado: " + user.getCorreo());
        
        System.out.println("Correo: " + user.getCorreo() + ", Nombre: " + user.getNombre() );
        
        return new User(user.getCorreo(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }
}
