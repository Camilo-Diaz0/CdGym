package cdgym.entities;

import java.time.Instant;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;
    
    @NotBlank
    private String apellido;

    @Column(unique = true)
    @Min(100000000) 
    @Digits(integer = 10, fraction = 0)
    private Integer documento;

    @Digits(integer = 10, fraction = 0)
    @Min(1000000000)
    private Long telefono;

    @Temporal(TemporalType.DATE)
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiracionMensualidad;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyy")
    private Date inicioMensualidad;
    
    public Cliente(){}
    public Cliente(String nombre, String apellido, Integer documento, Long telefono, Date expiracionMensualidad, Date inicioMensualidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.expiracionMensualidad = expiracionMensualidad;
        this.inicioMensualidad = inicioMensualidad;
    }

    public boolean mensualidadExpirada(){
        Date fechaActual = Date.from(Instant.now());
        if(fechaActual.compareTo(this.expiracionMensualidad) <= 0) return false;
        return true;
    }
    
    public Long getId(){
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Date getExpiracionMensualidad() {
        return expiracionMensualidad;
    }

    public void setExpiracionMensualidad(Date expiracionMensualidad) {
        this.expiracionMensualidad = expiracionMensualidad;
    }

    public Date getInicioMensualidad() {
        return inicioMensualidad;
    }
    public void setInicioMensualidad(Date inicioMensualidad) {
        this.inicioMensualidad = inicioMensualidad;
    }
    @Override
    public String toString() {
        return "Cliente => \n   id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento
                + ", telefono=" + telefono + ",\n expiracionMensualidad=" + expiracionMensualidad +
                ",\n inicio=" + inicioMensualidad;
    }
}
