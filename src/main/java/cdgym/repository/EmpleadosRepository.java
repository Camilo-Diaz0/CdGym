package cdgym.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cdgym.entities.Empleados;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados,Long>{

}