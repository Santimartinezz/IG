/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocvio;

import java.util.List;
import modelo.mascota.Mascota;

/**
 *
 * @author Alumno
 */
public interface IABMMascota {
    
    public void altaMascota(Mascota mascota,int idDuenio);
    
    public void bajMascota(Mascota mascota);
    
    public void modificacionMascota(Mascota mascota);
    
    public void listarMascota();
    
    public void altaMascotaArray(Mascota mascota);
    
    public void bajaMascotaArray(Mascota mascota);
    
    public void bajaMascotaArray(String codigo);
    
    public void modificacionMascotaArray(Mascota mascota);
    
    public Mascota  buscarMascota(String codigo);
    
     public Integer buscarMascotaIndice(String codigo);
    
    public void listarMascotaArray();
    
    public String nuevoCodigoMascota();
    
    public List<?> listarMascotaPorDuenio(int dni);
    
}
