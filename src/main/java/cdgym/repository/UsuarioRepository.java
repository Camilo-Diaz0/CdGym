package cdgym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cdgym.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
