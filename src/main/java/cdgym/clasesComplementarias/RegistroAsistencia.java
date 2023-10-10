package cdgym.clasesComplementarias;

import java.util.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class RegistroAsistencia {
    @Temporal(TemporalType.DATE)
    private Date fechaAsistencia;
    private boolean asistencia;
    private String jornada;

    RegistroAsistencia(){}

    public RegistroAsistencia(Date fechaAsistencia,boolean asistencia, String jornada) {
        this.fechaAsistencia = fechaAsistencia;
        this.asistencia = asistencia;
        this.jornada = jornada;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    @Override
    public String toString() {
        return "RegistroAsistencia: \n    fechaAsistencia = " + fechaAsistencia + "\n    asistencia = " + asistencia 
        + "\n    jornada = "+ jornada;
    }
    
    
    
}
