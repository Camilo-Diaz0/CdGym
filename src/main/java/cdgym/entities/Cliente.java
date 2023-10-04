package cdgym.entities;

import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Integer documento;
    private Integer telefono;
    private Date expiracionMensualidad;
    
    public Cliente() {}
    public Cliente(String nombre, String apellido, Integer documento, Integer telefono, Date expiracionMensualidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.expiracionMensualidad = expiracionMensualidad;
    }

    public boolean mensualidadExpirada(){
        Date fechaActual = Date.from(Instant.now());
        if(fechaActual.compareTo(this.expiracionMensualidad) <= 0) return false;
        return true;
    }

    public Long getId(){
        return this.id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Date getExpiracionMensualidad() {
        return expiracionMensualidad;
    }

    public void setExpiracionMensualidad(Date expiracionMensualidad) {
        this.expiracionMensualidad = expiracionMensualidad;
    }

    @Override
    public String toString() {
        return "Cliente => \n   id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento
                + ", telefono=" + telefono + ", expiracionMensualidad=" + expiracionMensualidad;
    }    
}
