package cdgym.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cdgym.entities.Empleado;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleado,Long>{

    @Query("select e from Empleado e join fetch e.asistenciasRegistradas a where e.id=?1")
    public Empleado findByIdwithAsistencias(Long id);
}