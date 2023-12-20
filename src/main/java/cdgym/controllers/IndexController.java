package cdgym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cdgym.entities.Cliente;
import cdgym.entities.Empleado;
import cdgym.entities.Usuario;
import cdgym.service.ClienteService;
import cdgym.service.EmpleadosService;
import jakarta.validation.Valid;

@Controller
public class IndexController {
    
    @Autowired
    EmpleadosService empleadosService;
    
    @Autowired
    ClienteService clienteService;

    @GetMapping({"","/","/index"})
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String cargarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }
    @PostMapping("/iniciarsesion")
    public String login(@Valid Usuario usuario, BindingResult result){
        System.out.println(usuario);
        if(result.hasErrors()){
            System.out.println("tiwnw errores");
            result.getAllErrors().forEach(System.out::println);
            return "login";
        }
        System.out.println(usuario);
        return "redirect:/";
    }
    @GetMapping("/registro")
    public String cargarRegistro(Model model) {
        model.addAttribute("usuario", null);
        return "registro";
    }
    @PostMapping("/validarRegistro")
    public String ValidarRegistro(@RequestParam Integer documento, Model model){
        Empleado empleado = empleadosService.getEmpleadoByDocumento(documento);
        if(empleado != null) {
            model.addAttribute("mensaje", "Documento validado exitosamente," 
            +" realice su registro a continuacion");
            Usuario usuario = new Usuario();
            usuario.setEmpleado(empleado);
            model.addAttribute("usuario", usuario);
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
            System.out.println("\n"+usuario);
            result.getAllErrors().forEach(System.out::println);
            return "registro";
        }
        System.out.println(usuario);
        return "redirect:/";
    }
    
    @GetMapping("/clientes")
    public String consultarCliente(){
        return "clientes";
    }

    @PostMapping("/clientes")
    public String consultarCliente(@RequestParam Integer documento, Model model){
        Cliente cliente= clienteService.getCliente(documento);
        model.addAttribute("cliente", cliente);
        return "clientes";
    }
    
}
