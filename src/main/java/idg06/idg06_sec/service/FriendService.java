package idg06.idg06_sec.service;

import idg06.idg06_sec.model.entity.Friend;
import idg06.idg06_sec.model.entity.Usuario;
import idg06.idg06_sec.model.repositories.FriendRepository;
import idg06.idg06_sec.model.repositories.UsuarioRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {

    private final FriendRepository friendRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository, UsuarioRepository usuarioRepository) {
        this.friendRepository = friendRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void agregarAmigo(String correoPropietario, Long amigoId) {
        System.out.println("Correo del propietario: " + correoPropietario);
        System.out.println("Id del amigo: " + amigoId);
        // Buscar al propietario por su correo
        Usuario propietario = usuarioRepository.findByCorreo(correoPropietario);
        // Buscar al amigo por su ID
        Usuario amigo = usuarioRepository.findById(amigoId).orElse(null);

        System.out.println("Este es el propietario: " + propietario);
        System.out.println("Este es el amigo: " + amigo);

        if (propietario != null && amigo != null) {
            Friend nuevaAmistad = new Friend();
            nuevaAmistad.setMyUser(propietario);
            nuevaAmistad.setFriend(amigo);
            nuevaAmistad.setFecha(new Date());
            friendRepository.save(nuevaAmistad);
            System.out.println("Entro aqui, estoy desde el service");
        } else {
            System.out.println("No se insertó usuario");
        }
    }

    public void eliminarAmigo(String correoPropietario, Long amigoId) {
        Optional<Friend> propietarioOptional = friendRepository.findByMyUser_Correo(correoPropietario);
        Optional<Friend> amigoOptional = friendRepository.findById(amigoId);

        if (propietarioOptional.isPresent() && amigoOptional.isPresent()) {
            Friend propietario = propietarioOptional.get();
            Friend amigo = amigoOptional.get();

            // Eliminar la amistad
            friendRepository.delete(propietario);
        }
    }

    // Resto de métodos:
    public List<Friend> findAllExceptPropietario(String correoPropietario) {
        return friendRepository.findAllExceptPropietario(correoPropietario);
    }

    public Friend findById(Long id) {
        return friendRepository.findById(id).orElse(null);
    }

    public Friend save(Friend friend) {
        return friendRepository.save(friend);
    }
}
