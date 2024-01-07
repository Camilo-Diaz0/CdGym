package cdgym.controllers;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import cdgym.clasesComplementarias.RegistroAsistencia;
import cdgym.entities.Cliente;
import cdgym.entities.Empleado;
import cdgym.service.ClienteService;
import cdgym.service.EmpleadosService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(value="/empleado")
public class EmpleadoController {
    
    @Autowired
    private EmpleadosService service;
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value="/crear")
    public String crearEmpleado(Model model){
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("titulo", "Registrar empleado");
        return "crearEmpleado";
    }

    @PostMapping(value = "/crear")
    public String crearEmpleado(@Valid Empleado empleado, RedirectAttributes flash){
        service.save(empleado);
        flash.addFlashAttribute("success", "Empleado creado exitosamente");
        return "redirect:/";
    }

    @GetMapping(value="/listar")
    public String listarEmpleados(Model model){
        List<Empleado> empleados = service.getEmpleados();
        model.addAttribute("empleados", empleados);
        model.addAttribute("titulo", "Listado de empleados");
        return "listarEmpleados";
    }
    @GetMapping(value = "/informacion/{id}")
    public String datos(@PathVariable Long id, Model model){
        Empleado empleado = service.getEmpleado(id);
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", "Informacion de empleado");
        return "informacionEmpleado";
    }
    @GetMapping(value = "/registrarMensualidad/")
    public String mensualidad(Model model){
        model.addAttribute("titulo", "Registro de mensualidad");
        return "mensualidad";
    }
    @PostMapping(value="/registrarMensualidad/mensualidad")
    public String mensualidad(@RequestParam Integer documento, @RequestParam Integer opcMensualidad,Model model, RedirectAttributes flash){
        Cliente cliente = clienteService.getCliente(documento);
        if(cliente == null){
            flash.addFlashAttribute("error", "El documento ingresado no pertenece a ningun cliente de CDGYM."+
            " Intente de nuevo o registre un nuevo cliente");
            return "redirect:/empleado/registrarMensualidad/";
        }
        cliente = clienteService.procesarMensualidad(cliente, opcMensualidad);
        clienteService.saveCliente(cliente);
        flash.addFlashAttribute("success", "Mensualidad del cliente registrada exitosamente");
        return "redirect:/";
    }
    @GetMapping(value = "/registrarMensualidad/cliente")
    public String registrarCliente(Model model){
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("titulo", "Registro de mensualidad");

        return "mensualidad";
    }
    @PostMapping(value = "/registrarMensualidad/cliente")
    public String registrarCliente(Cliente cliente, @RequestParam Integer opcMensualidad, RedirectAttributes flash){
        cliente = clienteService.procesarMensualidad(cliente, opcMensualidad);
        clienteService.saveCliente(cliente);
        flash.addFlashAttribute("success", "Cliente y mensualidad registrados exitosamente");
        return "redirect:/";
    }
    @GetMapping("/instructores")
    public String listarInstructores(Model model){
        model.addAttribute("empleados", service.getEmpleadosByCargo("Instructor"));
        model.addAttribute("titulo", "Lista de Instructores");

        return "listaInstructores";
    }
    @GetMapping("/asistencia/{id}")
    public String asistencia(@PathVariable Long id, Model model){
        Empleado empleado = service.getEmpleado(id);
        RegistroAsistencia asistencia = new RegistroAsistencia();
        asistencia.setFechaAsistencia(Date.from(Instant.now()));
        asistencia.setAsistencia(true);
        model.addAttribute("asistencia", asistencia);
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", "Registro de asistencia del instructor");
        return "asistencia";
    }
    
    @PostMapping("/asistencia")
    public String guardarAsistencia(@RequestParam Long idEmpleado, RegistroAsistencia asistencia, RedirectAttributes flash){
        Empleado empleado = service.getEmpleado(idEmpleado);
        asistencia.setFechaAsistencia(Date.from(Instant.now()));
        List<RegistroAsistencia> asistenciasRegistradas = empleado.getAsistenciasRegistradas();
        asistenciasRegistradas.add(asistencia);
        empleado.setAsistenciasRegistradas(asistenciasRegistradas);
        service.save(empleado);
        flash.addFlashAttribute("success", "Asistencia registrada exitosamente");
        return "redirect:/empleado/instructores";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id, RedirectAttributes flash){
        service.eliminarEmpleado(id);
        flash.addFlashAttribute("success", "Empleado eliminado exitosamente");
        return "redirect:/empleado/listar";
    }
}