/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorio.dao.mascota.perro;

import java.util.List;
import modelo.mascota.Perro;

/**
 *
 * @author Alumno
 */
public interface IDaoPerro {
    
    public Integer altaPerro(Perro perro,int dni);
    
    public List<Perro> listarPerrosPorDuenio(int dni);
    
    public Perro buscarPerroPorCodigo(String codigo);
    
    public Integer proximoCodigo();
    
    
    
}
