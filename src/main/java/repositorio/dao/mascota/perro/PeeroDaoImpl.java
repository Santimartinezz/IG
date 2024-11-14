/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio.dao.mascota.perro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.mascota.Perro;
import modelo.persona.duenio.Duenio;
import repositorio.dao.conexion.ConexionDB;
import repositorio.dao.persona.DuenioDaoImpl;
import repositorio.dao.persona.IDaoDuenio;

/**
 *
 * @author Alumno
 */
public class PeeroDaoImpl implements IDaoPerro{
    
    private ConexionDB conexionDB;
    private IDaoDuenio iDaoDuenio= new DuenioDaoImpl();
    
    @Override
    public Integer altaPerro(Perro perro, int dni) {
        String sqlInsetMascota=" insert into mascotas(id_duenio,nombre,edad,peso,sexo,tipo,raza,codigo)"
                + " values(?,?,?,?,?,?,?,?)";
        Duenio duenio=iDaoDuenio.buscarDuenioPorDni(dni);
        
        HashMap<Integer,Object> parametros= new HashMap<Integer,Object>();
        parametros.put(0, duenio.getId());
        parametros.put(1, perro.getNombre());
        parametros.put(2, perro.getEdad());
        parametros.put(3, perro.getPeso());
        parametros.put(4, (perro.getSexo()=='M')?"M":"H");
        parametros.put(5, 1);
        parametros.put(6, perro.getRaza());
        parametros.put(7, perro.getCodigoPerro());
        try {
            conexionDB= new ConexionDB();
            conexionDB.ejecutarSqlConParametros(sqlInsetMascota, parametros);
        }catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
            return -1;
        }
        return 1;
    }

    @Override
    public List<Perro> listarPerrosPorDuenio(int dni) {
        List<Perro> perros= new ArrayList<Perro>();
        String sqlCosultaMascota="SELECT * from mascotas m "
             + " INNER JOIN duenios d on m.id_duenio=d.id "
             + " INNER JOIN personas p on d.id_persona=p.id "
             + " WHERE m.tipo=1 and p.dni="+dni;
        try{   
           conexionDB= new ConexionDB();
           ResultSet rs=conexionDB.ejecutarConsultaSql(sqlCosultaMascota);
           while(rs.next()){
               Perro p= new Perro(rs.getString("m.raza"),rs.getString("m.nombre"), rs.getInt("m.edad"), rs.getFloat("m.peso"), rs.getString("m.sexo").charAt(0));
               p.setCodigoPerro(rs.getString("m.codigo"));
               perros.add(p);
           }
        }catch(SQLException e){} 
     return perros;
    }

    @Override
    public Integer proximoCodigo() {
     
        String sqlCodigo=" Select max(id) as codigo from mascotas where tipo=1";
        
        try {
            conexionDB= new ConexionDB();
            ResultSet rs=conexionDB.ejecutarConsultaSql(sqlCodigo);
            if(rs.next())
                return rs.getInt("codigo")+1;
        }catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
            return -1;
        }
        return 0;
    }

    @Override
    public Perro buscarPerroPorCodigo(String codigo) {
        String sqlCosultaMascota="SELECT * from mascotas " 
             + " WHERE tipo=1 and codigo='"+codigo+"'";
        try{   
           conexionDB= new ConexionDB();
           ResultSet rs=conexionDB.ejecutarConsultaSql(sqlCosultaMascota);
           if(rs.next()){
               Perro p= new Perro(rs.getString("raza"),rs.getString("nombre"), rs.getInt("edad"), rs.getFloat("peso"), rs.getString("sexo").charAt(0));
               p.setCodigoPerro(rs.getString("codigo"));
               return p;
           }
        }catch(SQLException e){} 
     return null;
    }
    
    
}
