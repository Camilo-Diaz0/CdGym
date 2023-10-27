package cdgym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cdgym.entities.Empleado;
import cdgym.entities.Usuario;
import cdgym.service.EmpleadosService;
import jakarta.validation.Valid;


@Controller
public class IndexController {
    
    @Autowired
    EmpleadosService empleadosService;

    @GetMapping({"","/","/index"})
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String cargarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }
    @PostMapping("/login")
    public String login(@Valid Usuario usuario, BindingResult result){
        if(result.hasErrors()){
            return "login";
        }
        System.out.println(usuario);
        return "index";
    }
    @GetMapping("/registro")
    public String cargarRegistro(Model model) {
        model.addAttribute("usuario", null);
        model.addAttribute("empleado", new Empleado());
        return "registro";
    }
    @PostMapping("/validarRegistro")
    public String ValidarRegistro(Empleado empleado, Model model){
        System.out.println(empleado.getDocumento());
        empleado = empleadosService.getEmpleadoByDocumento(empleado.getDocumento());
        System.out.println(empleado);
        if(empleado != null) {
            model.addAttribute("mensaje", "Documento validado exitosamente," 
            +" realice su registro a continuacion");
            model.addAttribute("usuario", new Usuario());
            model.addAttribute("documeto", empleado.getDocumento());
        }
        else {
            model.addAttribute("error","El documento ingresado no se encuentra"
            +" en la lista de empleados");
        }
        return "registro";
    }
    @PostMapping("/registro")
    public String registro(@Valid Usuario usuario, BindingResult result){
        if(result.hasErrors()){
            return "registro";
        }
        System.out.println(usuario);
        return "index";
    }
    


}
