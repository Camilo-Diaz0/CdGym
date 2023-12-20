package cdgym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import cdgym.entities.Cliente;
import cdgym.service.ClienteService;
import cdgym.service.EmpleadosService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(value="/empleado")
public class EmpleadoController {
    
    @Autowired
    private EmpleadosService service;
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value="/registrar")
    public String crearEmpleado(){
        service.getEmpleado(1l);
        return "index";
    }
    @GetMapping(value = "/registrarMensualidad/")
    public String mensualidad(){
        return "mensualidad";
    }
    @PostMapping(value="/registrarMensualidad/mensualidad")
    public String mensualidad(@RequestParam Integer documento, @RequestParam Integer opcMensualidad ,Model model){
        Cliente cliente = clienteService.getCliente(documento);
        if(cliente == null){
            return "mensualidad";
        }
        cliente = clienteService.procesarMensualidad(cliente, opcMensualidad);
        clienteService.saveCliente(cliente);
        return "redirect:/";
    }
    @GetMapping(value = "/registrarMensualidad/cliente")
    public String registrarCliente(Model model){
        model.addAttribute("cliente", new Cliente());
        return "mensualidad";
    }
    @PostMapping(value = "/registrarMensualidad/cliente")
    public String registrarCliente(Cliente cliente, @RequestParam Integer opcMensualidad){
        System.out.println(cliente + " : "+opcMensualidad);
        cliente = clienteService.procesarMensualidad(cliente, opcMensualidad);
        clienteService.saveCliente(cliente);
        return "redirect:/";
    }
}
