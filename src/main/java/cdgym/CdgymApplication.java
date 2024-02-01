package cdgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import cdgym.entities.Cliente;
import cdgym.entities.Empleado;
import cdgym.entities.Usuario;
import cdgym.service.ClienteService;
import cdgym.service.EmpleadosService;
import cdgym.service.UsuarioService;

@SpringBootApplication
public class CdgymApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(CdgymApplication.class, args);
		UsuarioService usuarioService = run.getBean(UsuarioService.class);
			EmpleadosService service = run.getBean(EmpleadosService.class);
			ClienteService clienteService = run.getBean(ClienteService.class);
			PasswordEncoder encoder = run.getBean(PasswordEncoder.class);
			Empleado empleado = new Empleado("Administrador", "Ramiro", "Quevedo", 1123578920);
			Usuario usuario = new Usuario("ramiro", encoder.encode("password"), empleado);
			usuario.setRole(usuarioService.gestionarRole(empleado.getCargo()));
			empleado.setUsuario(usuario);
			service.save(empleado);
			clienteService.saveCliente(new Cliente("camilo", "diaz", 1111111111, 3183183183l, null, null));
		
	}
}
