/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocvio.abm.turno;

import java.util.Date;
import java.util.List;
import modelo.turno.Turno;
import negocvio.abm.exception.turno.TurnoException;

/**
 *
 * @author Alumno
 */
public interface ITurno {

    public Integer altaTurno(Turno turno)throws TurnoException;
    
    public List<?> buscarTurno(int dniDuenio,String matriculaVet,String nombreMascota);
    
    public Turno buscarTurno(Integer id,int dniDuenio,Date fecha);
    
}
