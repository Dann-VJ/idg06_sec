/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import lombok.Data;

/**
 *
 * @author DannVJ
 */
@Entity
@Data
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    // username
    // @Column(length = 50, columnDefinition = "VARCHAR(50) DEFAULT 'user'")
    // private String username;
    // username
    // @Column(length = 50, nullable = false) // Agregar nullable = false para que no acepte valores nulos
    // private String username = "user"; // Valor predeterminado
    // nombre
    @Column(length = 50)
    @NotEmpty
    private String nombre;

    @Column(length = 50)
    @NotEmpty
    private String apellidos;

    // Correo
    @Column(length = 50, unique = true)
    @NotEmpty
    private String correo;

    // Contraseña
    @NotEmpty
    @Column(length = 255)
    @Size(min = 5, max = 255)
    private String password;

    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private List<Rol> authorities;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DatosPersonales> datosPersonales;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comentarios; // Comentarios realizados por el usuario

    public List<DatosPersonales> getDatosPersonales() {
        return datosPersonales;
    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> publicaciones = new ArrayList<>();

    public List<Post> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Post> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @OneToMany(mappedBy = "myUser")
    private List<Friend> amigos = new ArrayList<>(); // Cambio de Set a List 

    public void agregarAmigo(Usuario amigo) {
        if (amigo != null) {
            Friend amistad = new Friend();
            amistad.setMyUser(this); 
            amistad.setFriend(amigo);
            amistad.setFecha(new Date());
            amigos.add(amistad);
        }
    }

    public void eliminarAmigo(Usuario amigo) {
        amigos.removeIf(amistad -> amistad.getFriend().equals(amigo));
    }
    

}
