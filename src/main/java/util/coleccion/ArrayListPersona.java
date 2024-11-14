/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.coleccion;

import java.util.ArrayList;
import modelo.persona.duenio.Duenio;

import modelo.persona.Persona;
import modelo.persona.veterinaria.Veterinaria;
import modelo.persona.veterinario.Veterinario;

/**
 *
 * @author Alumno
 */
public class ArrayListPersona<E> extends ArrayList<Persona> {
    
    public int indexOf(Persona o) {
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i) instanceof Duenio){
                Duenio duo=(Duenio)o;
              if(this.get(i).getDni()==o.getDni())
                return i;
            }else{
                Veterinario vo=(Veterinario)o;
                if(this.get(i).getDni()==o.getDni())
                return i;
            } 
        }
        return -1;
    }
    
    public boolean contains(Persona o){
        boolean existe=false;
        for (Persona p : this) {
            if(p instanceof Duenio){
                Duenio d=(Duenio)p;
                Duenio duo=(Duenio)o;
                if(p.getDni()==duo.getDni())
                 return true;
            }else if(p instanceof Veterinario){
                Veterinario v=(Veterinario)p;
                Veterinario vo=(Veterinario)o;
                if(v.getDni()==vo.getDni())
                 return true;
                
            }else if(p instanceof Veterinaria){
                Veterinaria v=(Veterinaria)p;
                Veterinaria vo=(Veterinaria)o;
                if(v.getNombreFantasia().equals(vo.getNombreFantasia()) 
                        && v.getCuit().equals(vo.getCuit()))
                 return true;
            }
        }
        return existe;
    }
    
}
