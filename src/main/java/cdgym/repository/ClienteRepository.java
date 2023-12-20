package cdgym.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cdgym.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    public Optional<Cliente> findByDocumento(Integer documento);
}
