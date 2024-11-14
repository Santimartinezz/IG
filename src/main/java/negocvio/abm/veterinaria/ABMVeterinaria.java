/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocvio.abm.veterinaria;

import modelo.persona.Persona;
import modelo.persona.veterinaria.Veterinaria;
import modelo.persona.veterinario.Veterinario;
import repositorio.RepositorioDeDatos;

/**
 *
 * @author Alumno
 */
public class ABMVeterinaria implements IABMVeterinaria{
    
   

    @Override
    public void asignarVeterianio(String cuit,String matricula) {
        Veterinaria veterinaria= new Veterinaria();
        veterinaria.setCuit(cuit);
        int index=RepositorioDeDatos.personas.indexOf(veterinaria);
        if(index!=-1){
            veterinaria=(Veterinaria)RepositorioDeDatos.personas.get(index);
            Veterinario veterinario=new Veterinario();
            veterinario.setMatricula(matricula);
            int indexV=RepositorioDeDatos.personas.indexOf(veterinario);
            if(indexV!=-1){
                if(!veterinaria.getVeterinarios().contains((Veterinario)RepositorioDeDatos.personas.get(indexV))){
                    veterinaria.getVeterinarios().add((Veterinario)RepositorioDeDatos.personas.get(indexV));
                }else
                    System.out.println("El veterinario ya existe en la veterinaria");
            }else
                System.out.println("El veterinario no existe");
        }else
            System.out.println("La veterinaria no existe");
        
    }

    @Override
    public void altaPersona(String nombre, String apellidpo, int dni) {
        
    }

    @Override
    public void altaPersona(Persona persona) {
        Veterinaria veterinaria=(Veterinaria)persona;
        if(!validarDatosVeterinaria(veterinaria)){
            if(!RepositorioDeDatos.personas.contains(veterinaria))
                RepositorioDeDatos.personas.add(veterinaria);
            else
                System.out.println("La veterinaria ya existe ");
        }
    }

    @Override
    public void listarPersonas(int dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private boolean validarDatosVeterinaria(Veterinaria veterinaria){
        boolean blnValidar=false;
        if(veterinaria==null){
            System.out.println("El objeto Veterinaria no puede ser nulo");
            return true;
        }
        if(veterinaria.getNombreFantasia()==null || veterinaria.getNombreFantasia().equals("")){
            System.out.println("El nombre de fanasia no puede ser vacio");
            return true;
        }
        if(veterinaria.getCuit()==null || veterinaria.getCuit().equals("")){
            System.out.println("El cuit no puede ser vacio");
            return true;
        }
        
        if(veterinaria.getNombre()==null || veterinaria.getNombre().equals("")){
            System.out.println("El nombre no puede ser vacio");
            return true;
        }
         
        if(veterinaria.getApellido()==null || veterinaria.getApellido().equals("")){
            System.out.println("El apellido no puede ser vacio");
            return true;
        }
        if(veterinaria.getDni()<=0 || veterinaria.getDni()>Integer.MAX_VALUE){
            System.out.println("El nùmero de dni no esta conprendido entre los valores aceptados");
            return true;
        }
        return blnValidar;
    }
}
