/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mascota;

/**
 *
 * @author Alumno
 */
public abstract class Mascota {
    private Integer id;
    private String nombre;
    private int edad;
    private float peso;
    private char sexo;

    public Mascota(){
        super();
    }
    public Mascota(String nombre, int edad, float peso, char sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
    public abstract String toString();
    
    public abstract String generarCodigo();
}
