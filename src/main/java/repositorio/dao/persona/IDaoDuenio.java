/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorio.dao.persona;

import java.util.List;
import modelo.mascota.Mascota;
import modelo.persona.duenio.Duenio;


/**
 *
 * @author Alumno
 */
public interface IDaoDuenio {
    
   public Integer grabarDuenio(Duenio duenio);
    
    public Integer updateDuenio(Duenio duenio);
    
    public void eliminarDuenio(Integer id);
    
    public Duenio buscarDuenio(Integer id);
    
    public Duenio buscarDuenioPorDni(int dni);
    
    public List<Duenio> listraDuenio(String nombre , String apellido,int dni);
    
    public Integer asignarMascotaADuenio(int idDuenio,Mascota mascota);
}
