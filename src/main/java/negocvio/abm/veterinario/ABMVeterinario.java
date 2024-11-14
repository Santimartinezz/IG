/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocvio.abm.veterinario;

import modelo.persona.duenio.Duenio;
import modelo.persona.Persona;
import modelo.persona.veterinario.Veterinario;
import negocvio.IABMPersona;
import negocvio.abm.veterinario.exception.VeterinarioException;
import repositorio.RepositorioDeDatos;
import repositorio.dao.persona.IDaoVeterinario;
import repositorio.dao.persona.VeterinarioDaoImp;
import java.util.List;

/**
 *
 * @author Alumno
 */
public class ABMVeterinario implements IABMPersona {
    
    private IDaoVeterinario iDaoVeterinario= new VeterinarioDaoImp();
    
    @Override
    public void altaPersona(String nombre, String apellidpo, int dni)throws VeterinarioException {
      Duenio d= new Duenio(nombre, apellidpo, dni);
        altaPersona(d);   
    }

    @Override
    public void altaPersona(Persona persona)throws VeterinarioException{
        Veterinario veterinario=(Veterinario)persona;
        if(!validarDatosVeterinario((Veterinario)persona)){
            /*
            if(!RepositorioDeDatos.personas.contains(veterinario))
                RepositorioDeDatos.personas.add(veterinario);
            else
                System.out.println("El veterinario ya existe");
            */
            if(iDaoVeterinario.buscarVeterinarioPorMatricula(veterinario.getMatricula())==null){
                if(iDaoVeterinario.grabarVeterinario(veterinario)==1)
                    System.out.println("El veterinario se grabo corectamente");
                else
                    System.out.println("Error al intertar dar de alta un veterinario");
            }else
                System.out.println("El veterinario ya aexiste");
        }
    }

    @Override
    public void listarPersonas(int dni) {
       /*
        for (Persona persona : RepositorioDeDatos.personas) {
            if(persona instanceof Veterinario){
                Veterinario v=(Veterinario)persona;
                v.toString();
            }
        }
        */
       List<Veterinario> result=iDaoVeterinario.listraVeterinario(null, null, null);
        for (Veterinario veterinario : result) {
               System.out.println(veterinario.toString());
        }
    }
    
    private boolean validarDatosVeterinario(Veterinario v){
        if(v.getNombre()==null ||v.getNombre().equals("")){
            System.out.println("Ingrese el Nombre del veterinario");
            return true;
        }
        if(v.getApellido()==null || v.getApellido().equals("")){
            System.out.println("Ingrese el apellido del veterinario");
            return true;
        }
        if(v.getDni()==0){
            System.out.println("Ingrese el dni del veterinario");
            return true;
        }
        if(v.getMatricula()==null || v.getMatricula().equals("") ){
            System.out.println("Ingrese la matricula del veterinario");
            return true;
        }
        return false;
    }
}
