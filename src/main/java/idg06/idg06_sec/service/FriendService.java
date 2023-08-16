package idg06.idg06_sec.service;

import idg06.idg06_sec.model.entity.Friend;
import idg06.idg06_sec.model.repositories.FriendRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {

    private final FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public void agregarAmigo(String correoPropietario, Long amigoId) {
        Optional<Friend> propietarioOptional = friendRepository.findByMyUser_Correo(correoPropietario);
        Optional<Friend> amigoOptional = friendRepository.findById(amigoId);

        if (propietarioOptional.isPresent() && amigoOptional.isPresent()) {
            Friend propietario = propietarioOptional.get();
            Friend amigo = amigoOptional.get();

            // Crear una nueva amistad
            Friend nuevaAmistad = new Friend();
            nuevaAmistad.setMyUser(propietario.getMyUser());
            nuevaAmistad.setFriend(amigo.getMyUser()); // Aquí obtén el amigo desde el amigo encontrado
            friendRepository.save(nuevaAmistad);
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
