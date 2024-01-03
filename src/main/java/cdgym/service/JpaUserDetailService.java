package cdgym.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cdgym.entities.Usuario;
import cdgym.repository.UsuarioRepository;

@Service
public class JpaUserDetailService implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no encontrado"));
        
        List<GrantedAuthority> authorities = Arrays.stream(usuario.getRole().split(", "))
            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        
        return new User(usuario.getUsername(), usuario.getPassword(), authorities);

    }
}
