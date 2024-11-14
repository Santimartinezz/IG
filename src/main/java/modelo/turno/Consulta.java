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
public class Consulta  extends Turno{
    private String motivo;

    public Consulta(Integer id, Date fecha, Duenio duenio, Veterinario veterinario,
            Mascota mascota,String comentario,String motivo) {
        super(fecha,duenio,veterinario,mascota,comentario);
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
   
}
