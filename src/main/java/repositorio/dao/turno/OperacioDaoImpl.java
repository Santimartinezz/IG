/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio.dao.turno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import modelo.mascota.Perro;
import modelo.persona.duenio.Duenio;
import modelo.persona.veterinario.Veterinario;
import modelo.turno.Operacion;
import repositorio.dao.conexion.ConexionDB;
import repositorio.dao.mascota.perro.IDaoPerro;
import repositorio.dao.mascota.perro.PeeroDaoImpl;
import repositorio.dao.persona.DuenioDaoImpl;
import repositorio.dao.persona.IDaoDuenio;
import repositorio.dao.persona.IDaoVeterinario;
import repositorio.dao.persona.VeterinarioDaoImp;

/**
 *
 * @author Alumno
 */
public class OperacioDaoImpl  implements IDaoTurnoOperacion{
    
    private ConexionDB conexionDB;
    private IDaoDuenio iDaoDuenio= new DuenioDaoImpl();
    private IDaoVeterinario idDaoVeterinario= new VeterinarioDaoImp();
    private IDaoPerro IDaoPerro= new PeeroDaoImpl();
    
    
    @Override
    public Integer altaTurnoOperacion(Operacion operacion) {
        String sqlInsert=" insert into turnos(fecha,id_duenio,id_veterinario,id_mascota,tipo_turno,tipo_operacion,terminos)"
                +" values(?,?,?,?,?,?,?)";
         HashMap<Integer,Object> parametros= new HashMap<Integer,Object>();
         parametros.put(0, operacion.getFecha());
         parametros.put(1, operacion.getDuenio().getIdDuenio());
         parametros.put(2, operacion.getVeterinario().getIdVeterinario());
         parametros.put(4, operacion.getMascota().getId());
         parametros.put(5, "OPERACION");
         parametros.put(6, operacion.getTipo());
         parametros.put(7, operacion.getTerminos());
         conexionDB= new ConexionDB();
         try{
            conexionDB.ejecutarSqlConParametros(sqlInsert, parametros);
         }catch(SQLException e){
             System.out.println(e.getMessage());
             return -1;
         }
        return 1;
    }

    @Override
    public List<Operacion> listarTurnoOperacion(Date fecha, String matricula, int dniDuenio) {
         List<Operacion> operaciones= new ArrayList<Operacion>();
        String sqlConsultaTurno=" select * from turnos t "
               + " inner join duenios d on t.id_duenio=d.id "
               + " inner join personas p on d.id_persona=p.id "
               + " inner join veterinarios v on t.id_veterinario=v.id "
               + " inner join personas pv on v.id_persona=pv.id "
               + " inner join mascotas m on t.id_mascota=m.id "
               + " where 1=1";
        
        if(fecha!=null && !fecha.equals(""))
            sqlConsultaTurno=sqlConsultaTurno+ " and t.fecha='"+fecha+"'";
        
        if(matricula!=null && !matricula.equals(""))
            sqlConsultaTurno=sqlConsultaTurno+ " and v.matricula='"+matricula+"'";
        
        if(dniDuenio>0)
            sqlConsultaTurno=sqlConsultaTurno+ " and p.dni="+dniDuenio;
        
        conexionDB= new ConexionDB();
        try{
            ResultSet rs=conexionDB.ejecutarConsultaSql(sqlConsultaTurno);
            while(rs.next()){
                Duenio duenio= iDaoDuenio.buscarDuenio(rs.getInt("t.id_duenio"));
                Veterinario vet=idDaoVeterinario.buscarVeterinario(rs.getInt("t.id_veterinario"));
                Operacion op=null;
                if(rs.getInt("m.tipo")==1){
                    Perro p= IDaoPerro.buscarPerroPorCodigo(rs.getString("m.codigo"));
                    op=new Operacion(rs.getString("t.tipo_turno"), rs.getString("t.termionos"), rs.getDate("t.fecha"), duenio, vet, p, "");
                }                 
                operaciones.add(op);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return operaciones;
    }

    @Override
    public Operacion buscarTurnoOperacion(Integer id, int dniDuenio, Date fecha) {
        String sqlConsultaTurno=" select * from turnos t "
               + " inner join duenios d on t.id_duenio=d.id "
               + " inner join veterinarios v on t.id_veterinario=v.id "
               + " inner join mascotas m on t.id_mascota=m.id "
               + " where 1=1";
        
        if(id!=null && id>0)
            sqlConsultaTurno=sqlConsultaTurno+ " and t.id"+id;
        
        if(fecha!=null && !fecha.equals(""))
            sqlConsultaTurno=sqlConsultaTurno+ " and t.fecha='"+fecha+"'";
        
        if(dniDuenio>0)
            sqlConsultaTurno=sqlConsultaTurno+ " and d.dni="+dniDuenio;
        
        conexionDB= new ConexionDB();
        try{
            ResultSet rs=conexionDB.ejecutarConsultaSql(sqlConsultaTurno);
            if(rs.next()){
                Duenio duenio= iDaoDuenio.buscarDuenio(rs.getInt("t.id_duenio"));
                Veterinario vet=idDaoVeterinario.buscarVeterinario(rs.getInt("t.id_veterinario"));
                Operacion op=null;
                if(rs.getInt("m.tipo")==1){
                    Perro p= IDaoPerro.buscarPerroPorCodigo(rs.getString("m.codigo"));
                    return new Operacion(rs.getString("t.tipo_turno"), rs.getString("t.termionos"), rs.getDate("t.fecha"), duenio, vet, p, "");
                }                 
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
