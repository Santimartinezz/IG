/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorio.dao.turno;

import java.util.Date;
import java.util.List;
import modelo.turno.Operacion;

/**
 *
 * @author Alumno
 */
public interface IDaoTurnoOperacion {
    
    public Integer altaTurnoOperacion(Operacion operacion);
    
    public List<Operacion> listarTurnoOperacion(Date fecha,String matricula,int dniDuenio);
    
    public Operacion buscarTurnoOperacion(Integer id, int dniDuenio, Date fecha);
    
}
