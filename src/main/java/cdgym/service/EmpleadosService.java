package cdgym.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cdgym.entities.Empleado;
import cdgym.entities.Usuario;
import cdgym.repository.EmpleadosRepository;
import cdgym.repository.UsuarioRepository;

@Service
public class EmpleadosService {
    
    @Autowired
    private EmpleadosRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public EmpleadosService(){}

    public void save(Empleado empleado){
        repository.save(empleado);
    }
    public void mostrarRepositorio(){
        System.out.println(this.repository);
    }
    public void guardarUsuario(Usuario user){
        usuarioRepository.save(user);
    }
    public Empleado getEmpleado(Long id){
        Empleado empleado= repository.findByIdwithAsistencias(id);
        return empleado;
    }
}
