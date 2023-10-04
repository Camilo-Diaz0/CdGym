package cdgym.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cdgym.entities.Empleados;
import cdgym.repository.EmpleadosRepository;

@Service
public class EmpleadosService {
    
    @Autowired
    private EmpleadosRepository repository;

    public EmpleadosService(){}

    public void save(Empleados empleado){
        repository.save(empleado);
    }
    public void mostrarRepositorio(){
        System.out.println(this.repository);
    }
}
