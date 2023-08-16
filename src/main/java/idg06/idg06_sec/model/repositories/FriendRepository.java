/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idg06.idg06_sec.model.repositories;

import idg06.idg06_sec.model.entity.Friend;
import idg06.idg06_sec.model.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author DannVJ
 */
public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByMyUser_Correo(String correo);

    @Query("SELECT f FROM Friend f WHERE f.myUser.correo <> ?1")
    List<Friend> findAllExceptPropietario(String correo);
}

