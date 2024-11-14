/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio.dao.persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.persona.Persona;
import modelo.persona.veterinario.Veterinario;
import repositorio.dao.conexion.ConexionDB;

/**
 *
 * @author Alumno
 */
public class VeterinarioDaoImp implements IDaoVeterinario {
    
    private ConexionDB comm= new ConexionDB();
    private static final String SLQ_INSERT_PERDONA=" INSERT INTO personas (nombre,apellido,dni)Values(?,?,?)";
    private static final String SQL_GET_PERSONA="SELECT * FROM personas WHERE dni=?"; 
    
    
    @Override
    public Integer grabarVeterinario(Veterinario veterinario){
        String sqlInsertVeterinario="insert into veterinarios(id_persona,matricula) values(?,?)";
        try{
        HashMap<Integer,Object> parametros= new HashMap<Integer,Object>();
        parametros.put(0, veterinario.getNombre());
        parametros.put(1, veterinario.getApellido());
        parametros.put(2, veterinario.getDni());
        comm.ejecutarSqlConParametros(SLQ_INSERT_PERDONA, parametros);
        parametros.clear();
        parametros.put(0, veterinario.getDni());
        ResultSet rs=comm.ejecutarConsultaSqlConParametros(SQL_GET_PERSONA, parametros);
        
        if(rs.next()){
            int idPersona=rs.getInt("id");
            parametros.clear();
            parametros.put(0, idPersona);
            parametros.put(1, veterinario.getMatricula());
            comm.ejecutarSqlConParametros(sqlInsertVeterinario, parametros);
            
        }
        }catch(SQLException e){
            return -1;
        }
        return 1;
    }

    @Override
    public Integer updateVeterinario(Veterinario veterinario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarVeterinario(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Veterinario buscarVeterinario(Integer id) {
       String sqlQuery="SELECT * FROM veterinarios v "
               + " INNER JOIN personas p on p.id= v.id_persona "
               + " where v.id=?";
       Veterinario veterinario=null;
       HashMap<Integer,Object> parametros= new HashMap<Integer,Object>();
       parametros.put(0, id);
       try{
        ResultSet rs =comm.ejecutarConsultaSqlConParametros(sqlQuery, parametros);
        if (rs.next()) 
           veterinario=new Veterinario(rs.getString("matricula"), rs.getString("nombre"), rs.getString("apellido"),rs.getInt("dni"));         
       }catch(SQLException e){
           System.out.println("Error: "+e.getMessage()); 
       }
       return veterinario;
    }

    @Override
    public List<Veterinario> listraVeterinario(String nombre, String apellido, String matricula) {
        List<Veterinario> veterinarios= new ArrayList<Veterinario>();
        String sqlConsultaVet="SELECT * FROM veterinarios v "
                + " INNER JOIN personas p ON p.id=v.id_persona "
                + " WHERE 1=1 ";
         
        if(nombre!=null && !nombre.equals(""))
             sqlConsultaVet=sqlConsultaVet + " AND p.nombre='"+nombre+"'";
         
         if(apellido!=null && !apellido.equals(""))
             sqlConsultaVet=sqlConsultaVet + " AND p.apellido='"+apellido+"'";
         
         if(matricula!=null && !matricula.equals(""))
             sqlConsultaVet=sqlConsultaVet + " AND v.matricula='"+matricula+"'";
         
         try{
         
         ResultSet rs=comm.ejecutarConsultaSql(sqlConsultaVet);
         
         while(rs.next()){
             Veterinario vet= new Veterinario(rs.getString("matricula"), rs.getString("nombre"), rs.getString("apellido"),rs.getInt("dni"));
             veterinarios.add(vet);
         }
         }catch(SQLException e){
             System.out.println("Error: "+ e.getMessage());
         }
        return veterinarios;
    }

    @Override
    public Veterinario buscarVeterinarioPorMatricula(String matricula) {
        String sqlQuery="SELECT * FROM veterinarios v "
                +" INNER JOIN personas p on p.id= v.id_persona "
                +" where v.matricula=?";
       Veterinario veterinario=null;
       HashMap<Integer,Object> parametros= new HashMap<Integer,Object>();
       parametros.put(0, matricula);
       try{
        
        ResultSet rs =comm.ejecutarConsultaSqlConParametros(sqlQuery, parametros);
        if (rs.next()) 
           veterinario=new Veterinario(rs.getString("matricula"), rs.getString("nombre"), rs.getString("apellido"),rs.getInt("dni"));         
       }catch(SQLException e){
           System.out.println("Error: "+e.getMessage()); 
       }
       return veterinario;
    }
    
    
}
