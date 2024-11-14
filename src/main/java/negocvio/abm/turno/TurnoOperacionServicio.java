/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocvio.abm.turno;

import java.util.Date;
import java.util.List;
import modelo.turno.Operacion;
import modelo.turno.Turno;
import negocvio.abm.exception.turno.TurnoException;
import repositorio.dao.turno.IDaoTurnoOperacion;
import repositorio.dao.turno.OperacioDaoImpl;

/**
 *
 * @author Alumno
 */
public class TurnoOperacionServicio extends TurnoServcioImpl{
    
    private IDaoTurnoOperacion iDaoTurnoOperacion= new OperacioDaoImpl() ;
    
    @Override
    public Integer altaTurno(Turno turno)throws TurnoException {
        validarTurno(turno);
        if(iDaoTurnoOperacion.buscarTurnoOperacion(null, turno.getDuenio().getDni(),turno.getFecha())==null){
            iDaoTurnoOperacion.altaTurnoOperacion((Operacion)turno);
            System.out.println("El turno se registro de forma correcta");
        }else
             System.out.println("Ya existe un turno para los datos ingresados");

       return 1;
    }

    @Override
    public List<?> buscarTurno(int dniDuenio, String matriculaVet, String nombreMascota) {
      return iDaoTurnoOperacion.listarTurnoOperacion(null, matriculaVet, dniDuenio);
    }

    @Override
    public Turno buscarTurno(Integer id, int dniDuenio, Date fecha) {
        return iDaoTurnoOperacion.buscarTurnoOperacion(id, dniDuenio, fecha);
    }
    
    private void validarTurno(Turno turno)throws TurnoException{
        
        if(turno==null)
            throw new TurnoException("El paràmetro de entrada no puede ser nulo");
        
        if(turno.getDuenio()==null)
            throw new TurnoException("Seleccione un duenio");
        
        if(turno.getVeterinario()==null)
            throw new TurnoException("Seleccione un veterianrio");
        
        if(turno.getMascota()==null)
            throw new TurnoException("Seleccione una mascota");
        
        if(turno.getFecha()==null || turno.getFecha().equals("") )
            throw new TurnoException("Ingrese la fecha del turno");
    }
}
