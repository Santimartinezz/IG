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
import modelo.mascota.Mascota;
import repositorio.dao.conexion.ConexionDB;
import modelo.persona.duenio.Duenio;
/**
 *
 * @author Alumno
 */
public class DuenioDaoImpl  implements IDaoDuenio{
    
    private ConexionDB comm= new ConexionDB();
    private static final String SLQ_INSERT_PERDONA=" INSERT INTO personas (nombre,apellido,dni)Values(?,?,?)";
    private static final String SQL_GET_PERSONA="SELECT * FROM personas WHERE dni=?"; 

    @Override
    public Integer grabarDuenio(Duenio duenio) {
     String sqlInsertDuenio="insert into duenios(id_persona) values(?)";
        try{
        HashMap<Integer,Object> parametros= new HashMap<Integer,Object>();
        parametros.put(0, duenio.getNombre());
        parametros.put(1, duenio.getApellido());
        parametros.put(2, duenio.getDni());
        comm.ejecutarSqlConParametros(SLQ_INSERT_PERDONA, parametros);
        parametros.clear();
        parametros.put(0, duenio.getDni());
        ResultSet rs=comm.ejecutarConsultaSqlConParametros(SQL_GET_PERSONA, parametros);
        
        if(rs.next()){
            int idPersona=rs.getInt("id");
            parametros.clear();
            parametros.put(0, idPersona);
            comm.ejecutarSqlConParametros(sqlInsertDuenio, parametros);
            
        }
        }catch(SQLException e){
            return -1;
        }
        return 1;
    }

    @Override
    public Integer updateDuenio(Duenio duenio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarDuenio(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Duenio buscarDuenio(Integer id) {
        String sqlQuery="SELECT * FROM duenios d "
               + " INNER JOIN personas p on p.id= d.id_persona "
               + " where d.id=?";
       Duenio duenio=null;
       HashMap<Integer,Object> parametros= new HashMap<Integer,Object>();
       parametros.put(0, id);
       try{
        ResultSet rs =comm.ejecutarConsultaSqlConParametros(sqlQuery, parametros);
        if (rs.next()){ 
           duenio=new Duenio(rs.getString("nombre"), rs.getString("apellido"),rs.getInt("dni")); 
           duenio.setId(rs.getInt("id"));
        }
       }catch(SQLException e){
           System.out.println("Error: "+e.getMessage()); 
       }
       return duenio;
    }

    @Override
    public Duenio buscarDuenioPorDni(int dni) {
        String sqlQuery="SELECT * FROM duenios d "
               + " INNER JOIN personas p on p.id= d.id_persona "
               + " where p.dni=?";
       Duenio duenio=null;
       HashMap<Integer,Object> parametros= new HashMap<Integer,Object>();
       parametros.put(0, dni);
       try{
        ResultSet rs =comm.ejecutarConsultaSqlConParametros(sqlQuery, parametros);
        if (rs.next()){
           duenio=new Duenio(rs.getString("nombre"), rs.getString("apellido"),rs.getInt("dni")); 
           duenio.setId(rs.getInt("id"));
        }   
       }catch(SQLException e){
           System.out.println("Error: "+e.getMessage()); 
       }
       return duenio;
    }

    @Override
    public List<Duenio> listraDuenio(String nombre, String apellido, int dni) {
         List<Duenio> duenios= new ArrayList<Duenio>();
        String sqlConsultaDue="SELECT * FROM duenios d "
                + " INNER JOIN personas p ON p.id=d.id_persona "
                + " WHERE 1=1 ";
         
        if(nombre!=null && !nombre.equals(""))
             sqlConsultaDue=sqlConsultaDue + " AND p.nombre='"+nombre+"'";
         
         if(apellido!=null && !apellido.equals(""))
             sqlConsultaDue=sqlConsultaDue + " AND p.apellido='"+apellido+"'";
         
         if(dni>0)
             sqlConsultaDue=sqlConsultaDue + " AND p.dni="+dni;
         
         try{
         
         ResultSet rs=comm.ejecutarConsultaSql(sqlConsultaDue );
         
         while(rs.next()){
             Duenio duenio= new Duenio(rs.getString("nombre"), rs.getString("apellido"),rs.getInt("dni"));
             duenios.add(duenio);
         }
         }catch(SQLException e){
             System.out.println("Error: "+ e.getMessage());
         }
        return duenios;
    }

    @Override
    public Integer asignarMascotaADuenio(int idDuenio, Mascota mascota) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     
    
}
