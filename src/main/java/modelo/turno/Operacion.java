/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.turno;

import java.util.Date;
import modelo.mascota.Mascota;
import modelo.persona.duenio.Duenio;
import modelo.persona.veterinario.Veterinario;

/**
 *
 * @author Alumno
 */
public class Operacion extends Turno{
    private String tipo;
    private String terminos;

    public Operacion() {
        super();
    }

    public Operacion(String tipo, String terminos) {
        this.tipo = tipo;
        this.terminos = terminos;
    }

    public Operacion(String tipo, String terminos, Date fecha, Duenio duenio, Veterinario veterinario, Mascota mascota, String comentario) {
        super(fecha, duenio, veterinario, mascota, comentario);
        this.tipo = tipo;
        this.terminos = terminos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTerminos() {
        return terminos;
    }

    public void setTerminos(String terminos) {
        this.terminos = terminos;
    }
    
}
