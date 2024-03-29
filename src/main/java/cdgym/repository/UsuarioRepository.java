package cdgym.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cdgym.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);
}
