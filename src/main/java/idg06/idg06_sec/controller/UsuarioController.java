/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package idg06.idg06_sec.controller;

import idg06.idg06_sec.model.entity.DatosPersonales;
import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author DannVJ
 */
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/insertData")
    public String insertData(@ModelAttribute DatosPersonales datosPersonales) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoUsuario = authentication.getName();
        Usuario usuario = usuarioService.findByCorreo(correoUsuario);

        // Agregar logs de depuración para verificar los valores de los atributos
        System.out.println("Datos recibidos desde el formulario:");
        System.out.println("Formación académica anterior: " + datosPersonales.getFormacionAcademicaAnterior());
        System.out.println("Carrera: " + datosPersonales.getCarrera());
        System.out.println("....: " + datosPersonales.getUsuario());

        datosPersonales.setUsuario(usuario);
        usuario.getDatosPersonales().add(datosPersonales);
        usuarioService.saveUsuario(usuario);
        return "redirect:/profile";
    }

}
