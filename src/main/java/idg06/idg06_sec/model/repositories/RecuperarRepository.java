package idg06.idg06_sec.model.repositories;

import idg06.idg06_sec.model.entity.Recuperar;       
import idg06.idg06_sec.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecuperarRepository extends JpaRepository<Recuperar, Long> {
    Recuperar findByUsuarioAndToken(Usuario usuario, String token);
}