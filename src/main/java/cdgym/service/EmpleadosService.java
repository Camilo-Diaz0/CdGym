package cdgym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        Empleado empleado = repository.findByDocumento(documento).orElseThrow();
        return empleado;
    }
    public void eliminarEmpleado(Long id){
        repository.deleteById(id);
    }

}
