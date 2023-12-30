package cdgym.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cdgym.entities.Empleado;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleado,Long>{

    @Query("select e from Empleado e join fetch e.asistenciasRegistradas a where e.id=?1")
    public Optional<Empleado> findByIdwithAsistencias(Long id);
    
    public Optional<Empleado> findByDocumento(Integer documento);
    public List<Empleado> findAllByCargo(String cargo);
}