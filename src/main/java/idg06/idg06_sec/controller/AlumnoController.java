/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.controller;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import idg06.idg06_sec.model.repositories.AlumnoRepository;
import idg06.idg06_sec.model.entity.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author DannVJ
 */
@Controller
public class AlumnoController {

    @Autowired
    private AlumnoRepository repo;

    /**
     * @GetMapping("/") public String pagePpal() { return "index"; }
     */
    @GetMapping("/alumnos")
    public String pageAlumnos(Model model) {
        model.addAttribute("lista", repo.findAll());
        return "alumnos";
    }

    @GetMapping("/alumnos_agregar") // dev tool actualiza los datos 
    public String pageAlumnosAdd(Model model, Alumno alumno) {
        return "frmAlumnosAdd";
    }
}
