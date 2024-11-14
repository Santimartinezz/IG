/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocvio.abm.veterinaria;

import modelo.persona.veterinaria.Veterinaria;
import negocvio.IABMPersona;

/**
 *
 * @author Alumno
 */
public interface IABMVeterinaria extends IABMPersona{
    
    public void asignarVeterianio(String cuit,String matricula);
}
