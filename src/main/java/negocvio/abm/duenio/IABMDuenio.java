/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocvio.abm.duenio;

import modelo.mascota.Mascota;
import modelo.persona.duenio.Duenio;
import negocvio.IABMPersona;

/**
 *
 * @author Alumno
 */
public interface IABMDuenio extends IABMPersona {
 
    public void asignarMacotaAduenio(int dni,Mascota mascota);
    
    public void eliminarMascotaDeDuenio(int dni,String codigo);
    
    public boolean modificarMascotaDeDuenio(int dni,String codigo,String nombre,int edad,float peso,char sexo);
    
    public void listaTodasrMascotasPorDuenio();
    
    public void listarMascotasPorDuenio(int dni);
    
    public Duenio buscarDuenioPorDni(int dni);
}
