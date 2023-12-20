package cdgym.service;

import java.time.Instant;
import java.util.Date;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cdgym.entities.Cliente;
import cdgym.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    public ClienteRepository repository;

    public ClienteService(){}

    public void saveCliente(Cliente cliente){
        repository.save(cliente);
    }
    public Cliente getCliente(Integer documento){
        try{
            Cliente cliente = repository.findByDocumento(documento).orElseThrow();
            return cliente;
        }catch(NoSuchElementException e){
            return null;
        }
    }
    public Cliente procesarMensualidad(Cliente cliente, Integer opcMensualidad){
        Date fechaActual = Date.from(Instant.now());
        Date expiracionMensualidad;
        long dias =0l;
        if(opcMensualidad == 1) dias =15;
        else if(opcMensualidad == 2) dias=30l;
        else if(opcMensualidad == 3) dias=30*3l;
        else if(opcMensualidad == 4) dias=30*12l;
        expiracionMensualidad = Date.from(Instant.ofEpochMilli((fechaActual.getTime()+(1000*3600*24*dias))));
        System.out.println(fechaActual);
        System.out.println(expiracionMensualidad);
        cliente.setExpiracionMensualidad(expiracionMensualidad);
        cliente.setInicioMensualidad(fechaActual);
        return cliente;
    }
}
