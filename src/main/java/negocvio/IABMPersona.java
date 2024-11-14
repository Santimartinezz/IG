/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocvio;


import modelo.persona.Persona;
import negocvio.abm.exception.duenio.DuenioException;
import negocvio.abm.veterinario.exception.VeterinarioException;

/**
 *
 * @author Alumno
 */
public interface IABMPersona {
    
    public void altaPersona(String nombre,String apellidpo,int dni)throws DuenioException,VeterinarioException;
    
    public void altaPersona(Persona persona)throws DuenioException,VeterinarioException;
      
    public void listarPersonas(int dni);
}
