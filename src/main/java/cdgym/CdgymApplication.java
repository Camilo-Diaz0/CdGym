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
		EmpleadosService service = run.getBean(EmpleadosService.class);
		Empleado empleado = new Empleado("instructor", "Ramiro", "Quevedo", 1123578920);
		service.save(empleado);
		System.out.println(empleado.getDocumento());
		

	}

}
