/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mascota;

import repositorio.RepositorioDeDatos;

/**
 *
 * @author Alumno
 */
public class Perro extends Mascota implements IMascota {
    
    private String raza;
    private String codigoPerro;

    public Perro(String raza) {
        this.raza = raza;
    }
    
    public Perro(){
        super();
        //this.codigoPerro=generarCodigo();
    }
    
    public Perro(String raza, String nombre, int edad, float peso, char sexo) {
        super(nombre, edad, peso, sexo);
        this.raza = raza;
        //this.codigoPerro=generarCodigo();
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setCodigoPerro(String codigoPerro) {
        this.codigoPerro = codigoPerro;
    }
    
    public String getCodigoPerro() {
        return codigoPerro;
    }
    
    public String toString(){
        return "ES PERRO " + " CODIGO: " +this.generarCodigo()+ " NOMBRE: "+ this.getNombre()+ " EDAD: "+getEdad()
             + " PESO: "+ getPeso()+ " SEXO: "
             +getSexo()+" RAZA: "+getRaza();
    }

    @Override
    public boolean estaVacunado() {
        return false;
    }

    @Override
    public boolean esAdoptado() {
     return false;   
    }

    @Override
    public String generarCodigo() {
        return "P-"+contarCantidadDePerros();
    }
    
    private int contarCantidadDePerros(){
        int cantidad=0;
        for(int i=0;i<RepositorioDeDatos.mascotas.length-1;i++){
            if(RepositorioDeDatos.mascotas[i]!=null && RepositorioDeDatos.mascotas[i] instanceof Perro )
                cantidad++;
        }
        return cantidad;
    }
    
}
