/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package idg06.idg06_sec.model.repositories;

import idg06.idg06_sec.model.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author danielvj
 */
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    public Alumno findByMatricula(String matricula);
}
