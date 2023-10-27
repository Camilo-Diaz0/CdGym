package cdgym.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cdgym.entities.Usuario;
import cdgym.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(){}

    public void guardarUsuario(Usuario usuario){
        repository.save(usuario);
    }
    public Usuario getUsuario(Long id){
        Optional<Usuario> usuOptional = repository.findById(id);
        if(usuOptional.isPresent()) return usuOptional.get();
        return null;

    }
    public void eliminarUsuario(Long id){
        repository.deleteById(id);
    }

}
