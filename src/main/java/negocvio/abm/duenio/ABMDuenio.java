/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocvio.abm.duenio;

import java.util.ArrayList;
import java.util.List;
import modelo.persona.duenio.Duenio;
import modelo.mascota.Gato;
import modelo.mascota.Mascota;
import modelo.mascota.Perro;
import modelo.persona.Persona;
import negocvio.IABMMascota;
import negocvio.abm.exception.duenio.DuenioException;
import negocvio.abm.perro.ABMPerro;
import repositorio.RepositorioDeDatos;
import repositorio.dao.persona.DuenioDaoImpl;
import repositorio.dao.persona.IDaoDuenio;


/**
 *
 * @author Alumno
 */
public class ABMDuenio implements IABMDuenio{
    
    private IDaoDuenio iDaoDuenio=new DuenioDaoImpl();
    private IABMMascota iABMMascota;
    
    @Override
    public void altaPersona(String nombre, String apellidpo, int dni)throws DuenioException {
        Duenio d= new Duenio(nombre, apellidpo, dni);
        altaPersona(d);
    }

    @Override
    public void altaPersona(Persona persona)throws DuenioException {
        Duenio duenio=(Duenio)persona;
        validarDatosDuenio((Duenio)persona);
        /*
        if(!RepositorioDeDatos.personas.contains(duenio))
            RepositorioDeDatos.personas.add(duenio);
        else
            System.out.println("El dueño ya existe"); 
        */
        if(iDaoDuenio.buscarDuenioPorDni(duenio.getDni())==null){
            if(iDaoDuenio.grabarDuenio(duenio)==1)
                System.out.println("El duenio se grabo correctamente");
            else
                System.out.println("Error al intentar dar de alta el duenio");
        }else
            System.out.println("El Dueño ya existe");
    }

    @Override
    public void listaTodasrMascotasPorDuenio() {
        System.out.println("====== LISTADO DE MASCOTAS POR DUEÑO ====================");
        
        for (Persona persona : RepositorioDeDatos.personas) {
            Duenio duenio=(Duenio)persona;
            System.out.println(duenio.toString());
            System.out.println("============== LITA DE MASCOTA========================");
            for (Mascota mascota : duenio.getMascotas()) {
                if(mascota instanceof Perro){
                    Perro p=(Perro)mascota;
                    System.out.println(p.toString());
                }else{
                    Gato g=(Gato)mascota;
                    System.out.println(g.toString());
                }
            }
        }
        System.out.println("================== FIN LIATDO================================");
    }

    @Override
    public void asignarMacotaAduenio(int dni, Mascota mascota) {
        /*
        Duenio d= new Duenio();
        d.setDni(dni);
        Integer index=RepositorioDeDatos.personas.indexOf(d);
        if(index!=-1){
         d=(Duenio)RepositorioDeDatos.personas.get(index);
         if(!d.getMascotas().contains(mascota))
            d.getMascotas().add(mascota);
         else
                System.out.println("La mascota ya està asignada pal dueño");
        }
        */
        
        if(mascota instanceof Perro){
            iABMMascota= new ABMPerro();
            iABMMascota.altaMascota((Perro)mascota,dni);
        }
    }

    @Override
    public Duenio buscarDuenioPorDni(int dni) {
      return iDaoDuenio.buscarDuenioPorDni(dni);
    }
    
    
    
    @Override
    public void listarMascotasPorDuenio(int dni) {
        System.out.println("====== LISTADO DE MASCOTAS POR DUEÑO ====================");
        for (Persona persona : RepositorioDeDatos.personas) {
            Duenio duenio=(Duenio)persona;
            System.out.println(duenio.toString());
           if(duenio.getDni()==dni){
             System.out.println("============== LITA DE MASCOTA========================");
            for (Mascota mascota : duenio.getMascotas()) {
                if(mascota instanceof Perro){
                    Perro p=(Perro)mascota;
                    System.out.println(p.toString());
                }else{
                    Gato g=(Gato)mascota;
                    System.out.println(g.toString());
                }
            }
           }
        }
        System.out.println("================== FIN LIATDO================================");
    }

    @Override
    public void listarPersonas(int dni) {
        
        List<Duenio> duenios= new ArrayList<Duenio>();
        
        duenios=iDaoDuenio.listraDuenio("", "", 0);
       
        System.out.println("================ INICIO  LISTADO ===================");
        for (Duenio duenio : duenios) {
            System.out.println(duenio.toString());
        }
        System.out.println("================ FIN LISTADO ===================");
        System.out.println("Cantidad de dueños: "+duenios.size());
    }

    @Override
    public void eliminarMascotaDeDuenio(int dni, String codigo) {
        Duenio d=new Duenio();
        d.setDni(dni);
        d=(Duenio)RepositorioDeDatos.personas.get(RepositorioDeDatos.personas.indexOf(d));
        if(d!=null){
            Perro p=new Perro();
            p.setCodigoPerro(codigo);
            if(d.getMascotas()!=null && d.getMascotas().contains(p)){
                d.getMascotas().remove(d.getMascotas().indexOf(p));
                System.err.println("El perro con còdigo: "+codigo+" se elimino de forma correcta");
            }else{
                Gato g = new Gato();
                g.setCodigoGato(codigo);
                if(d.getMascotas()!=null && d.getMascotas().contains(g)){
                    d.getMascotas().remove(d.getMascotas().indexOf(g));
                    System.err.println("El gato con còdigo: "+codigo+" se elimino de forma correcta");
                }
            }
        }else
            System.err.println("Los datos de entrada no pueden ser nulos");
    }

    @Override
    public boolean modificarMascotaDeDuenio(int dni, String codigo, String nombre, int edad, float peso, char sexo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    private void validarDatosDuenio(Duenio d)throws DuenioException{
        if(d.getNombre()==null ||d.getNombre().equals(""))
            throw new DuenioException("Ingrese el Nombre del dueño");
        if(d.getApellido()==null || d.getApellido().equals(""))
            throw new DuenioException("Ingrese el apellido del dueño");
        if(d.getDni()==0)
            throw new DuenioException("Ingrese el dni del dueño");
    }
}
