package cdgym.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cdgym.entities.Empleado;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleado,Long>{

}