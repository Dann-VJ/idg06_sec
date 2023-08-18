package idg06.idg06_sec.controller;

import idg06.idg06_sec.model.entity.Recuperar;
import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.model.repositories.RecuperarRepository;
import idg06.idg06_sec.model.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class recuperarController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RecuperarRepository recuperarRepository;

   @GetMapping("/recuperarPass")
    public Object recuperarContraseña(@RequestParam String correo) {
        Usuario usuarioExistente = usuarioRepository.findByCorreo(correo);

        if (usuarioExistente != null) {
            // Generar un token de 10 caracteres
            String token = generarToken(10);

            Recuperar nuevaRecuperacion = new Recuperar();
            nuevaRecuperacion.setUsuario(usuarioExistente);
            nuevaRecuperacion.setToken(token);
            nuevaRecuperacion.setFecha(new Date()); // Fecha actual
            nuevaRecuperacion.setEnabled(true); // Habilitar la recuperación

            recuperarRepository.save(nuevaRecuperacion);

            return token;
        } else {
            return null;
        }
    }


    private String generarToken(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(caracteres.length());
            token.append(caracteres.charAt(index));
        }

        return token.toString();
    }
    
    
    @GetMapping("/validarToken")
    public ResponseEntity<String> validarToken(@RequestParam String correo, @RequestParam String token, @RequestParam String password) {
        Usuario usuarioExistente = usuarioRepository.findByCorreo(correo);

        if (usuarioExistente != null) {
            Recuperar recuperacion = recuperarRepository.findByUsuarioAndToken(usuarioExistente, token);
            if (recuperacion != null) {
                usuarioExistente.setContraseña(password); // Actualizar la contraseña proporcionada
                usuarioRepository.save(usuarioExistente);
                return ResponseEntity.ok("Contraseña actualizada exitosamente.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }


}