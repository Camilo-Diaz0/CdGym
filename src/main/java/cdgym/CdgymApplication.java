package cdgym;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cdgym.clasesComplementarias.RegistroAsistencia;
import cdgym.entities.Empleado;
import cdgym.entities.Usuario;
import cdgym.service.EmpleadosService;



@SpringBootApplication
public class CdgymApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(CdgymApplication.class, args);
		System.out.println("empezando");
		EmpleadosService service = run.getBean(EmpleadosService.class);
		service.guardarUsuario(new Usuario("camilo", "hola", null));
		Empleado empleado = new Empleado("instructor", "Ramiro", "Quevedo", 1123578920);
		RegistroAsistencia asistencia = new RegistroAsistencia(new Date(), true, "nocturna");
		ArrayList<RegistroAsistencia> asistenciasRegistradas = new ArrayList<>();
		asistenciasRegistradas.add(asistencia);
		empleado.setAsistenciasRegistradas(asistenciasRegistradas);
		service.save(empleado);
		empleado =service.getEmpleado(1l);
		System.out.println(empleado);

	}

}
