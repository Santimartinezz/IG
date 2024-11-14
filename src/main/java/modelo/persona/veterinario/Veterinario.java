/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.persona.veterinario;

import modelo.persona.Persona;

/**
 *
 * @author Alumno
 */
public class Veterinario extends Persona{
    
    private Integer idVeterinario;
    private String matricula;

    public Veterinario() {
        super();
    }

    public Veterinario(String matricula, String nombre, String apellido, int dni) {
        super(nombre, apellido, dni);
        this.matricula = matricula;
    }

    public Integer getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Integer idVeterinario) {
        this.idVeterinario = idVeterinario;
    }
    
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public String toString(){
        return "NOMBRE: "+this.getNombre()+ " APELLIDO: "
                +this.getApellido()
                + " DNI: "+this.getDni()
                + " MATRICULA: "+this.getMatricula();
    }
    
}
