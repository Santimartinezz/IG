/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package principal;

//import modelo.mascota.*;
import modelo.mascota.Perro;
import modelo.mascota.Gato;
import modelo.mascota.IMascota;
import negocvio.abm.perro.ABMPerro;
import repositorio.RepositorioDeDatos;
/**
 *
 * @author Alumno
 */
public class Clase2InterfazGrafica {

    public static void main(String[] args) {
        ABMPerro ambPerro= new ABMPerro();
        Perro p1= new Perro();
        p1.setNombre("PEPE");
        p1.setSexo('M');
        p1.setEdad(2);
        p1.setRaza("Boxer");
        //ambPerro.altaMascota(p1);
        
        
        //System.out.println("Nombre: "+p1.getNombre()+ " Edad: "+p1.getEdad()+ " Raza: "+p1.getRaza());
    
        Perro p2= new Perro("MANTO NEGRO","Tobi", 5, 22.5F, 'F');
        
        //ambPerro.altaMascota(p2);
       // System.out.println("Nombre: "+p2.getNombre()+ " Edad: "+p2.getEdad()+ " Raza: "+p2.getRaza());
       
        Gato g1= new Gato();
        g1.setNombre("Tom");
        g1.setEdad(3);
        g1.setPeso(3.5F);
        g1.setRaza("SIN RAZA");
        g1.setSexo('M');
        //System.out.println("Nombre: "+g1.getNombre()+ " Edad: "+g1.getEdad()+ " Raza: "+g1.getRaza());
        
        Perro p3= new Perro("GALGO","ALFONZO", 12, 30.5F, IMascota.SEXO_FEMENINO);
        //ambPerro.altaMascota(p3);
        /*
        System.out.println(p3.toString());
        
        RepositorioDeDatos.mascotas[0]=p1;
        RepositorioDeDatos.mascotas[1]=g1;
        RepositorioDeDatos.mascotas[2]=p2;
        RepositorioDeDatos.mascotas[3]=p3;
        
        System.out.println("Listar Mascotas");
        
        for(int i=0;i<RepositorioDeDatos.mascotas.length-1;i++){
            if(RepositorioDeDatos.mascotas[i]!=null)
                if(RepositorioDeDatos.mascotas[i] instanceof Perro)
                    System.out.println(((Perro)RepositorioDeDatos.mascotas[i]).toString());
                else
                    System.out.println(((Gato)RepositorioDeDatos.mascotas[i]).toString());
        }
        */
        /*
        Perro[]perros= new Perro[5];
        Gato[]gatos= new Gato[5];
        
        perros[0]=p1;
        perros[1]=p2;
        perros[2]=p3;
        
        for(int i=0;i<perros.length;i++){
            if(perros[i]!=null)
                System.out.println("Nombre: "+perros[i].getNombre()+ " Edad: "+perros[i].getEdad()+ " Raza: "+perros[i].getRaza());
        }
        
        gatos[0]=g1;
        

        for(int i=0;i<gatos.length;i++){
            if(gatos[i]!=null)
                System.out.println("Nombre: "+gatos[i].getNombre()+ " Edad: "+gatos[i].getEdad()+ " Raza: "+gatos[i].getRaza());
        }
        */
        //ambPerro.altaMascota(p1);
        ambPerro.listarMascota();
    }
}
