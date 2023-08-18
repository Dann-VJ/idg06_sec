/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idg06.idg06_sec.controller;

import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author DannVJ
 */
@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/signup")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "signup";
    }

    @PostMapping("/signup")
    public String procesarFormularioRegistro(@ModelAttribute("usuario") @Valid Usuario usuario,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        
        // Verificar si el correo tiene el dominio y si ya está registrado
        String emailDominio = usuario.getCorreo();
        String[] partes = emailDominio.split("@");
        String nDomain = partes[1];
        if (!nDomain.equals("uteq.edu.mx")) {
            result.rejectValue("correo", "error.usuario", "Correo es invalido");
            return "signup";
        } else if (usuarioService.existeCorreo(usuario.getCorreo())) {
            result.rejectValue("correo", "error.usuario", "Ya existe un usuario con este correo");
            return "signup";
        }

        // Registrar el nuevo usuario
        usuarioService.registrarUsuario(usuario);
        return "redirect:/login"; // Redirigir a una página de éxito de registro
    }
    
    @GetMapping("/recuperar")
    public String recuperarcontrasenaView(Model model) {
        
        return "recuperarContra";
    }
}
