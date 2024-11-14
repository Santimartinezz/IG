/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocvio.abm.gato;

import java.util.List;
import modelo.mascota.Gato;
import modelo.mascota.Mascota;
import modelo.mascota.Perro;
import negocvio.IABMMascota;
import repositorio.RepositorioDeDatos;

/**
 *
 * @author Alumno
 */
public class ABMGato implements IABMMascota{

    @Override
    public void altaMascota(Mascota mascota,int idDuenio) {
       
    }

    @Override
    public void bajMascota(Mascota mascota) {
        
    }

    @Override
    public void modificacionMascota(Mascota mascota) {
        
    }

    @Override
    public void listarMascota() {
        
    }

    @Override
    public void altaMascotaArray(Mascota mascota) {
      if(mascota!=null){
          if(RepositorioDeDatos.mascotasArray!=null && !RepositorioDeDatos.mascotasArray.contains(mascota))
              RepositorioDeDatos.mascotasArray.add((Perro)mascota);
          else
              System.out.println("La mascota que intenta ingresar ya existe");
      }else
            System.out.println("Los datos de entrada no pueden ser nulo");
    }

    @Override
    public void bajaMascotaArray(Mascota mascota) {
       if(mascota!=null)
         bajaMascotaArray(((Perro)mascota).getCodigoPerro());
       else
            System.out.println("El objeto de tipo Perro no puede ser nulo.");
    }

    @Override
    public void bajaMascotaArray(String codigo) {
        if(codigo!=null && !codigo.isEmpty() && !codigo.equals("")){
            Gato pBuscado=(Gato)buscarMascota(codigo);
            if(pBuscado!=null)
                RepositorioDeDatos.mascotasArray.remove(pBuscado);
        }else
            System.out.println("El còdigo ingresado no puede ser nulo");
            
    }
    @Override
    public Mascota buscarMascota(String codigo) {
        /*
        Perro p = new Perro();
        p.setCodigoPerro(codigo);
        if(RepositorioDeDatos.mascotasArray.contains(p)){
            for (Mascota mascota : RepositorioDeDatos.mascotasArray) {
                if(mascota instanceof Perro){
                    Perro pe=(Perro)mascota;
                    if(pe.getCodigoPerro().equals(codigo))
                        return pe;
                }
            }
        } 
        */
       Integer index=buscarMascotaIndice(codigo);
     return (index>=0)?RepositorioDeDatos.mascotasArray.get(index):null;
    }

    @Override
    public Integer buscarMascotaIndice(String codigo) {
       /*
        for(int i=0;i<RepositorioDeDatos.mascotasArray.size()-1;i++){
            if(RepositorioDeDatos.mascotasArray.get(i) instanceof Perro){
                if(((Perro)RepositorioDeDatos.mascotasArray.get(i)).getCodigoPerro().equals(codigo))
                    return i;
            }
        }
        */
       Gato g= new Gato();
       g.setCodigoGato(codigo);
        return RepositorioDeDatos.mascotasArray.indexOf(g);
    }
    
    
    
    @Override
    public void modificacionMascotaArray(Mascota mascota) {
      if(mascota!=null)
        modificarAtravesDeSet(mascota);
      else
         System.out.println("La mascota que se intenta modificar no puede contener datos nulos");
    }

    @Override
    public String nuevoCodigoMascota() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<?> listarMascotaPorDuenio(int dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    private void modificarAtravesDeSet(Mascota mascota){
        Gato g=(Gato)mascota;
        Integer indice=buscarMascotaIndice(g.getCodigoGato());
        if(indice!=-1)
            RepositorioDeDatos.mascotasArray.set(indice, (Perro)mascota);
    }
    
    private void modificarAtravesDeRemoveyAdd(Mascota mascota){
        Gato g=(Gato)mascota;
        Integer indice=buscarMascotaIndice(g.getCodigoGato());
        if(indice!=-1){
            RepositorioDeDatos.mascotasArray.remove(indice);
            RepositorioDeDatos.mascotasArray.add((Gato)mascota);
        }
    }

    @Override
    public void listarMascotaArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
