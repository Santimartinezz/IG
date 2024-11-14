/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.turno;

import java.util.Date;
import javax.swing.text.MaskFormatter;
import modelo.mascota.Mascota;
import modelo.persona.duenio.Duenio;
import modelo.persona.veterinario.Veterinario;

/**
 *
 * @author Alumno
 */
public abstract class Turno {
    
    private Integer id;
    private Date fecha;
    private Duenio duenio;
    private Veterinario veterinario;
    private Mascota mascota;
    private String comentario;
    
    public Turno(){
        super();
    }
    
    public Turno(Date fecha, Duenio duenio, Veterinario veterinario,
            Mascota mascota,String comentario) {
        super();
        this.fecha = fecha;
        this.duenio = duenio;
        this.veterinario = veterinario;
        this.mascota=mascota;
        this.comentario=comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
