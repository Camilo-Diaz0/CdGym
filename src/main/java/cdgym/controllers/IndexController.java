package cdgym.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cdgym.entities.Cliente;
import cdgym.entities.Empleado;
import cdgym.entities.Usuario;
import cdgym.service.ClienteService;
import cdgym.service.EmpleadosService;
import cdgym.service.UsuarioService;
import jakarta.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    EmpleadosService empleadosService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping({ "", "/", "/index" })
    public String index(Model model) {
        model.addAttribute("titulo", "CD GYM");
        return "index";
    }

    @GetMapping("/login")
    public String cargarLogin(Model model, Principal principal, RedirectAttributes flash,
        @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("titulo", "Inicio de sesion");
        if (principal != null){
            flash.addFlashAttribute("info", "No es posible ir al login debido a que ya ha iniciado sesion");
            return "redirect:/";
        }
        if(error != null){
            flash.addFlashAttribute("error", "Username o contraseña incorrecto, por favor vuelva a intentarlo");
            return "redirect:/login";
        }
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @GetMapping("/registro")
    public String cargarRegistro(Model model) {
        model.addAttribute("titulo", "Registro de Usuario");
        model.addAttribute("usuario", null);
        return "registro";
    }

    @PostMapping("/validarRegistro")
    public String ValidarRegistro(@RequestParam Integer documento, Model model) {
        Empleado empleado = empleadosService.getEmpleadoByDocumento(documento);
        if (empleado != null) {
            if (empleado.getUsuario() != null) {
                model.addAttribute("error", "El documento ingresado ya tiene un usuario asociado");
            } else {
                model.addAttribute("info", "Documento validado exitosamente,"
                        + " realice su registro a continuacion");
                Usuario usuario = new Usuario();
                usuario.setEmpleado(empleado);
                model.addAttribute("usuario", usuario);
            }
        } else {
            model.addAttribute("error", "El documento ingresado no se encuentra"
                    + " en la lista de empleados");
        }
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@Valid Usuario usuario, BindingResult result, RedirectAttributes flash) {
        if (result.hasErrors()) {
            System.out.println("\n" + usuario);
            result.getAllErrors().forEach(System.out::println);
            return "registro";
        }
        String role;
        role = usuarioService.gestionarRole(usuario.getEmpleado().getCargo());
        usuario.setRole(role);
        usuarioService.save(usuario);
        flash.addFlashAttribute("success", "Usuario registrado exitosamente");
        return "redirect:/";
    }

    @GetMapping("/clientes")
    public String consultarCliente(Model model) {
        model.addAttribute("titulo", "Informacion del cliente");
        return "clientes";
    }

    @PostMapping("/clientes")
    public String consultarCliente(@RequestParam Integer documento, Model model, RedirectAttributes flash) {
        Cliente cliente = clienteService.getCliente(documento);
        if(cliente == null){
            flash.addFlashAttribute("error", "El documento ingresado no se encuentra en la lista de clientes");
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "clientes";
    }

    @GetMapping("/error403")
    public String denegado(){
        return "403";
    }
}
