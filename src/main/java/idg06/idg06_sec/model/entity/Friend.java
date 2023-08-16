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
import java.util.Date;
import lombok.Data;

/**
 *
 * @author DannVJ
 */
@Entity
@Data
@Table(name = "amigos")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_myuser")
    private Usuario myUser;

    @ManyToOne
    @JoinColumn(name = "id_friend")
    private Usuario friend;

    private Date fecha;
    
    // Getter and Setter for 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for 'myUser'
    public Usuario getMyUser() {
        return myUser;
    }

    public void setMyUser(Usuario myUser) {
        this.myUser = myUser;
    }

    // Getter and Setter for 'friend'
    public Usuario getFriend() {
        return friend;
    }

    public void setFriend(Usuario friend) {
        this.friend = friend;
    }

    // Getter and Setter for 'fecha'
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    // evitar la recursión infinita
    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", fecha=" + fecha +
                // Omitir relaciones con usuarios y otras relaciones
                '}';
    }
}
