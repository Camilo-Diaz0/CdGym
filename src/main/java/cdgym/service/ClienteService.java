package cdgym.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cdgym.entities.Cliente;
import cdgym.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    public ClienteService(){}

    public Cliente saveCliente(Cliente cliente){
        try {
            cliente = repository.save(cliente);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            cliente = null;
        }
        return cliente;
    }
    public Cliente updateCliente(Cliente cliente){
        Cliente clienteActual = repository.findById(cliente.getId()).orElse(null);
        if(clienteActual != null){
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setTelefono(cliente.getTelefono());
            clienteActual.setExpiracionMensualidad(cliente.getExpiracionMensualidad());
            cliente = saveCliente(clienteActual);
            return cliente;
        }
        return null;
    }
    public Cliente getCliente(Integer documento){
        try{
            Cliente cliente = repository.findByDocumento(documento).orElseThrow();
            return cliente;
        }catch(NoSuchElementException e){
            return null;
        }
    }
    public Cliente getCliente(Long id){
        return repository.findById(id).orElse(null);
    }
    public List<Cliente> getAll(){
        return repository.findAll();
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
        cliente.setExpiracionMensualidad(expiracionMensualidad);
        cliente.setInicioMensualidad(fechaActual);
        return cliente;
    }
    public boolean documentoRepetido(Integer documento){
        Cliente cliente = getCliente(documento);
        if(cliente !=  null) return true;
        return false;
    }

}
