package cdgym.entities;

import cdgym.clasesComplementarias.RegistroAsistencia;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "empleado")
public class Empleado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Column(unique = true)
    @Digits(integer = 10, fraction = 0)
    @Min(1000000000)
    private Integer documento;

    @ElementCollection
    @CollectionTable(name = "asistencias", joinColumns = @JoinColumn(name = "empleado_id"))
    private List<RegistroAsistencia> asistenciasRegistradas;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "empleado", cascade = CascadeType.ALL)
    private Usuario usuario;

    public Empleado(){}
    public Empleado(String cargo, String nombre, String apellido, Integer documento) {
        this.cargo = cargo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        
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
    @Override
    public String toString() {
        String asistenciaString="";
        if(asistenciasRegistradas != null){
            asistenciaString = asistenciasRegistradas.stream().map(x -> x.toString()).reduce("", (a,b) -> a+b);
        }
    return "Empleado:{ \n  id=" + id + ", \n  cargo=" + cargo + ",\n  nombre=" + nombre + ",\n  apellido=" + apellido
                + ",\n  documento=" + documento + ",\n  asistenciasRegistradas=" 
                + asistenciaString + "\n}";
    }

    
    
}
