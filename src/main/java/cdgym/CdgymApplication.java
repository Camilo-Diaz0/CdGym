package cdgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cdgym.entities.Empleado;
import cdgym.service.EmpleadosService;

@SpringBootApplication
public class CdgymApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(CdgymApplication.class, args);
		System.out.println("empezando");
		Empleado empleado1 = new Empleado();
		empleado1.setUsername("CamiloD12");
		System.out.println(empleado1);
		EmpleadosService empleadosService = run.getBean(EmpleadosService.class);
		empleadosService.mostrarRepositorio();
		// empleadosService.save(empleado1);
	}

}
