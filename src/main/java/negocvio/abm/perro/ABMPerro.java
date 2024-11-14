/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocvio.abm.perro;

import java.util.List;
import modelo.mascota.Mascota;
import modelo.mascota.Perro;
import negocvio.IABMMascota;
import repositorio.RepositorioDeDatos;
import repositorio.dao.mascota.perro.IDaoPerro;
import repositorio.dao.mascota.perro.PeeroDaoImpl;

/**
 *
 * @author Alumno
 */
public class ABMPerro implements IABMMascota{
    
    private IDaoPerro iDaoPerro= new PeeroDaoImpl();
    
    @Override
    public void altaMascota(Mascota mascota,int idDuenio) {
        
       /*
        if(mascota!=null){
            if(!existe(((Perro)mascota).getCodigoPerro())){
                RepositorioDeDatos.mascotas[RepositorioDeDatos.indice]=(Perro)mascota;
                RepositorioDeDatos.indice++;
                System.out.println("La mascota se cargo coprrectamente");
            }else
                System.out.println("La mascota ya fue ingresada en otro momento");    
        }else
            System.out.println("El objecto no puede ser nulo");
        */
       if(mascota!=null){
           Perro perro=(Perro)mascota;
            if(iDaoPerro.buscarPerroPorCodigo(perro.getCodigoPerro())==null){
                iDaoPerro.altaPerro(perro, idDuenio);
                System.out.println("La mascota se cargo coprrectamente");
            }else
                System.out.println("La mascota ya fue ingresada en otro momento");    
        }else
            System.out.println("El objecto no puede ser nulo");
    }

    @Override
    public void bajMascota(Mascota mascota) {
        if(mascota!=null){
            int indice=buacrMascota(((Perro)mascota).getCodigoPerro());
            if(indice!=-1)
                RepositorioDeDatos.mascotas[indice]=null;
            else
                System.out.println("La mascota que intenta dar de baja no existe");
        }else
            System.out.println("El objeto no puede recibir datos nulos");
    }

    @Override
    public void modificacionMascota(Mascota mascota) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void listarMascota() {
        for(int i=0;i<RepositorioDeDatos.mascotas.length-1;i++){
            if(RepositorioDeDatos.mascotas[i]!=null)
                if(RepositorioDeDatos.mascotas[i] instanceof Perro)
                    System.out.println(((Perro)RepositorioDeDatos.mascotas[i]).toString());         
        }
    }

    @Override
    public String nuevoCodigoMascota() {
        return "P-"+iDaoPerro.proximoCodigo();
    }
    
    private boolean existe(String codigo){
           int i=0;
           while(i<RepositorioDeDatos.mascotas.length){
               if(RepositorioDeDatos.mascotas[i]!=null 
                       && RepositorioDeDatos.mascotas[i] instanceof Perro 
                       && ((Perro)RepositorioDeDatos.mascotas[i]).getCodigoPerro()!=null
                       && ((Perro)RepositorioDeDatos.mascotas[i]).getCodigoPerro().equals(codigo))
                   return true;
               i++;
           }
        return false;
    }

    @Override
    public List<?> listarMascotaPorDuenio(int dni) {
        return iDaoPerro.listarPerrosPorDuenio(dni);
    }
    
   
    private int buacrMascota(String codigo){
        /*int i=-1;
        for (Mascota mascota : RepositorioDeDatos.mascotas) {
            if(mascota!=null && mascota instanceof Perro)
                return i;
            i++;    
        }
        return i;
        */
        for(int i=0;i<RepositorioDeDatos.mascotas.length;i++){
            if(RepositorioDeDatos.mascotas[i]!=null 
                       && RepositorioDeDatos.mascotas[i] instanceof Perro 
                       && ((Perro)RepositorioDeDatos.mascotas[i]).getCodigoPerro().equals(codigo))
                       return i;
        }
        return -1;
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
            Perro pBuscado=(Perro)buscarMascota(codigo);
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
       Perro p= new Perro();
       p.setCodigoPerro(codigo);
        return RepositorioDeDatos.mascotasArray.indexOf(p);
    }
    
    
    
    @Override
    public void modificacionMascotaArray(Mascota mascota) {
      if(mascota!=null)
        modificarAtravesDeSet(mascota);
      else
         System.out.println("La mascota que se intenta modificar no puede contener datos nulos");
    }
    
    
    private void modificarAtravesDeSet(Mascota mascota){
        Perro p=(Perro)mascota;
        Integer indice=buscarMascotaIndice(p.getCodigoPerro());
        if(indice!=-1)
            RepositorioDeDatos.mascotasArray.set(indice, (Perro)mascota);
    }
    
    private void modificarAtravesDeRemoveyAdd(Mascota mascota){
        Perro p=(Perro)mascota;
        Integer indice=buscarMascotaIndice(p.getCodigoPerro());
        if(indice!=-1){
            RepositorioDeDatos.mascotasArray.remove(indice);
            RepositorioDeDatos.mascotasArray.add((Perro)mascota);
        }
    }
            
    @Override
    public void listarMascotaArray() {
       
    }
    
   
}
