/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.coleccion;

import java.util.ArrayList;
import modelo.mascota.Gato;
import modelo.mascota.Mascota;
import modelo.mascota.Perro;

/**
 *
 * @author Alumno
 */
public class ArrayListDeMascota<E> extends ArrayList<Mascota> {
    
 
    public int indexOf(Mascota o) {
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i) instanceof Perro){
                Perro p=(Perro)this.get(i);
                Perro po=(Perro)o;
                if(p.getCodigoPerro().equals(po.getCodigoPerro()))
                     return i;
            }else if(this.get(i) instanceof Gato){
                Gato g=(Gato)this.get(i);
                Gato go=(Gato)o;
                if(g.getCodigoGato().equals(go.getCodigoGato()))
                     return i;
            }
        }
        return -1;
    }
    public boolean contains(Mascota o){
        boolean existe=false;
        for (Mascota m : this) {
            if(m instanceof Perro){
                Perro p=(Perro)m;
                Perro po=(Perro)o;
                if(p.getCodigoPerro().equals(po.getCodigoPerro()))
                     return true;
            }else if(m instanceof Gato){
                Gato g=(Gato)m;
                Gato go=(Gato)o;
                if(g.getCodigoGato().equals(go.getCodigoGato()))
                     return true;
            }                
        }

        return existe;
    }
    
}
