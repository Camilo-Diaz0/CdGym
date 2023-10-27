package cdgym.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cdgym.service.EmpleadosService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value="/empleado")
public class EmpleadoController {
    
    @Autowired
    private EmpleadosService service;

    @GetMapping(value="/registrar")
    public String crearEmpleado(){
        
        return "index";
    }

}
