/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package idg06.idg06_sec.model.repositories;

import idg06.idg06_sec.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DannVJ
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Puedes agregar métodos personalizados de consulta si es necesario
}
