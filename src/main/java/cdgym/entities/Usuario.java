package cdgym.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String username;
    private String password;
    private String role;
    @OneToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    public Usuario(){}

    public Usuario(String username, String password, Empleado empleado) {
        this.username = username;
        this.password = password;
        this.empleado = empleado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        String mensaje="";
        if(empleado != null) mensaje = empleado.getNombre();
        return "Usuario:{ \n  id=" + id + ",\n  username=" + username 
        + ",\n  password=" + password + ",\n  role=" + role+ "\n  empleado asociado=" + mensaje  + "\n}";
    }
    
    
}
