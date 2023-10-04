package cdgym.entities;

import cdgym.clasesComplementarias.RegistroAsistencia;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cargo;
    private String nombre;
    private String apellido;
    private Integer documento;
    private List<RegistroAsistencia> asistenciasRegistradas;
    private Usuario usuario;

    public Empleado(){}
    public Empleado(String cargo, String nombre, String apellido, Integer documento) {
        this.cargo = cargo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.asistenciasRegistradas = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
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
    public List<RegistroAsistencia> getAsistenciasRegistradas() {
        return asistenciasRegistradas;
    }
    public void setAsistenciasRegistradas(List<RegistroAsistencia> asistenciasRegistradas) {
        this.asistenciasRegistradas = asistenciasRegistradas;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
