/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorio.dao.persona;

import java.sql.SQLException;
import java.util.List;
import modelo.persona.Persona;
import modelo.persona.veterinario.Veterinario;

/**
 *
 * @author Alumno
 */
public interface IDaoVeterinario {
    
    public Integer grabarVeterinario(Veterinario veterinario);
    
    public Integer updateVeterinario(Veterinario veterinario);
    
    public void eliminarVeterinario(Integer id);
    
    public Veterinario buscarVeterinario(Integer id);
    
    public Veterinario buscarVeterinarioPorMatricula(String matricula);
    
    public List<Veterinario> listraVeterinario(String nombre , String apellido, String matricula);
    
          
}
