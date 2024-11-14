/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.persona.veterinaria;

import modelo.persona.Persona;
import modelo.persona.veterinario.Veterinario;
import util.coleccion.ArrayListPersona;

/**
 *
 * @author Alumno
 */
public class Veterinaria extends Persona {
    
    private Integer idVeterinaria;
    private String nombreFantasia;
    private String cuit;
    private String email;
    private ArrayListPersona<Veterinario> veterinarios;

    
    public Veterinaria(){
        super();
    }
    
    public Veterinaria(String nombreFantasia, String cuit, String email, String nombre, String apellido, int dni) {
        super(nombre, apellido, dni);
        this.nombreFantasia = nombreFantasia;
        this.cuit = cuit;
        this.email = email;
    }

    public Integer getIdVeterinaria() {
        return idVeterinaria;
    }

    public void setIdVeterinaria(Integer idVeterinaria) {
        this.idVeterinaria = idVeterinaria;
    }
    
    
    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayListPersona<Veterinario> getVeterinarios() {
        return veterinarios;
    }

    public void setVeterinarios(ArrayListPersona<Veterinario> veterinarios) {
        this.veterinarios = veterinarios;
    }
    
}
