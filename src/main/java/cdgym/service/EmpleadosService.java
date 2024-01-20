package cdgym.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cdgym.clasesComplementarias.RegistroAsistencia;
import cdgym.entities.Empleado;
import cdgym.repository.EmpleadosRepository;

@Service
public class EmpleadosService {
    
    @Autowired
    private EmpleadosRepository repository;

    public EmpleadosService(){}

    public void save(Empleado empleado){
        repository.save(empleado);
    }
    public Empleado getEmpleadoConAsistencia(Long id){
        Empleado empleado= repository.findByIdwithAsistencias(id).orElseThrow();
        return empleado;
    }
    public Empleado getEmpleado(Long id){
        Empleado empleado = repository.findById(id).orElseThrow();
        return empleado;
    }
    public Empleado getEmpleadoByDocumento(Integer documento){
        try {
            Empleado empleado = repository.findByDocumento(documento).orElseThrow();
            return empleado;
        } catch (NoSuchElementException e) {
            return null;
        }
        
    }
    public void eliminarEmpleado(Long id){
        repository.deleteById(id);
    }
    public List<Empleado> getEmpleados(){
        return repository.findAll();
    }
    public List<Empleado> getEmpleadosByCargo(String cargo){
        return repository.findAllByCargo(cargo);
    }

    public boolean documentoRepetido(Integer documento){
         Empleado empleado = getEmpleadoByDocumento(documento);
        if(empleado !=  null) return true;
        return false;
    }
    public boolean asistenciaRepetida(List<RegistroAsistencia> asistencias){
        String ahora = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).toString();
        return asistencias.stream().map(as -> as.getFechaAsistencia())
                .filter(Objects::nonNull).map(fecha -> fecha.toString())
                .anyMatch(text -> text.equals(ahora));
    }
}
