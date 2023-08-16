/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package idg06.idg06_sec.controller;

import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.service.FriendService;
import idg06.idg06_sec.service.UsuarioService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DannVJ
 */
@Controller
public class FriendController {

    @Autowired
    private FriendService friendService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    /*
    @GetMapping("/pintar_usuarios")
    public String mostrarUsuarios(Model model, Principal principal) {
        String correoPropietario = principal.getName();
        List<Friend> amigos = friendService.findAllExceptPropietario(correoPropietario);
        System.out.println("Usuarios:" + amigos);
        model.addAttribute("amigos", amigos);
        return "index";
    }*/

    @GetMapping("/pintar_usuarios")
    public String mostrarUsuarios(Model model, Principal principal) {
        String correoPropietario = principal.getName();
        List<Usuario> usuarios = usuarioService.findAllUsersExceptPropietario(correoPropietario);
        model.addAttribute("usuarios", usuarios);
        return "index";
    }

    @PostMapping("/agregarAmigo")
    public String agregarAmigo(@RequestParam Long amigoId, Principal principal) {
        String correoUsuario = principal.getName();
        System.out.println(correoUsuario);
        System.out.println(amigoId);
        friendService.agregarAmigo(correoUsuario, amigoId);
        return "redirect:/pintar_usuarios";
    }

    @PostMapping("/eliminarAmigo")
    public String eliminarAmigo(@RequestParam Long amigoId, Principal principal) {
        String correoUsuario = principal.getName();
        friendService.eliminarAmigo(correoUsuario, amigoId);
        return "redirect:/";
    }

    /*
    @PostMapping("/eliminarAmigo")
    public String eliminarAmigo(@RequestParam Long amigoId, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoUsuario = authentication.getName();

        Friend propietarioAmigo = friendService.findByCorreo(correoUsuario);
        Friend amigo = friendService.findById(amigoId);

        if (propietarioAmigo != null && amigo != null) {
            propietarioAmigo.eliminarAmigo(amigo.getFriend()); // Utiliza el método adecuado de la clase Friend
            friendService.save(propietarioAmigo);
        } else {
            System.out.println("No se pudo eliminar el amigo.");
        }

        return "redirect:/";
    }*/
}
