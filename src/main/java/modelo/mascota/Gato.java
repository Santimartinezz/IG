/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.mascota;

/**
 *
 * @author Alumno
 */
public class Gato extends Mascota implements IMascota{
    private String raza;
    private String codigoGato;

    public Gato() {
       super();
    }
    public Gato(String raza) {
        this.raza = raza;
    }
    
    public Gato(String raza, String nombre, int edad, float peso, char sexo) {
        super(nombre, edad, peso, sexo);
        this.raza = raza;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getCodigoGato() {
        return codigoGato;
    }

    public void setCodigoGato(String codigoGato) {
        this.codigoGato = codigoGato;
    }
    
    @Override
    public String toString() {
     return "ES GATO NOMBRE: "+ getNombre()+ " EDAD: "+getEdad()
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
