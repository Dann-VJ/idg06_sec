/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package idg06.idg06_sec.controller;

import idg06.idg06_sec.model.entity.Comment;
import idg06.idg06_sec.model.entity.Post;
import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.service.CommentService;
import idg06.idg06_sec.service.PostService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files; // Importa la clase Files
import java.nio.file.Path; // Importa la clase Path
import java.nio.file.Paths; // Importa la clase Paths
import java.security.Principal;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import idg06.idg06_sec.service.UsuarioService;

/**
 *
 * @author DannVJ
 */
@Controller
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UsuarioService userService;
    @Autowired
    private CommentService commentService; // Agrega esta línea para declarar CommentService

    @PostMapping("/crearPublicacion")
    public String crearPublicacion(@RequestParam("texto") String texto,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoUsuario = authentication.getName();

        // Guardar la imagen en el sistema de archivos o en un servidor de almacenamiento
        String imagenPath = saveImage(imagen);

        // Crear una nueva instancia de Post
        Post nuevaPublicacion = new Post();
        nuevaPublicacion.setTexto(texto);
        nuevaPublicacion.setImagen(imagenPath);
        nuevaPublicacion.setEnabled(true);

        // Guardar la publicación en la base de datos asociada al usuario actual
        postService.createPost(nuevaPublicacion, correoUsuario);

        return "redirect:/";
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        String uploadDir = "src/main/resources/static/uploads/post";
        String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        // Guardar el archivo en la carpeta static/uploads/post
        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(imageFile.getBytes());
        }

        return "/uploads/post/" + fileName; // Devuelve la ruta relativa
    }

    @GetMapping("/publicacionesPorUsuario")
    public String publicacionesPorUsuario(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoUsuario = authentication.getName();

        List<Post> publicaciones = postService.findByUsuarioCorreo(correoUsuario);

        model.addAttribute("publicaciones", publicaciones);

        return "publicaciones";
    }

    // Mostramos las publicaciones
    @GetMapping("/")
    public String mostrarPublicaciones(Model model) {
        List<Post> publicaciones = postService.getAllPosts();
        model.addAttribute("publicaciones", publicaciones);
        return "index";
    }

    // Agregar comentario
    @PostMapping("/agregarComentario")
    public String agregarComentario(@RequestParam Long postId, @RequestParam String comentario, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correoUsuario = authentication.getName();

        Usuario currentUser = userService.findByCorreo(correoUsuario);
        if (currentUser != null) {
            Post post = postService.findById(postId); // Obtén la publicación

            Comment comment = new Comment();
            comment.setText(comentario);
            comment.setUsuario(currentUser); // Establece el usuario actual en el comentario
            comment.setPost(post); // Establece la publicación en el comentario

            commentService.saveComment(comment); // Guarda el comentario  
        } else {
            System.out.println("Lo sentimos no se pudo insertar tu comentario, el usuario no se encontró en la base de datos");
        }

        return "redirect:/"; // Redirecciona a la página de la publicación
    }

}
