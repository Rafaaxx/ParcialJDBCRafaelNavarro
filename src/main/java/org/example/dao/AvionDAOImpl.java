package org.example.dao;

import jdk.jfr.consumer.RecordingStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Avion;
import org.example.model.Vuelos;
import org.example.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvionDAOImpl implements VuelosDAO<Avion> {
    private static final Logger log = LogManager.getLogger(AvionDAOImpl.class);

    //CONEXION, STATEMENT Y RESULTSET
    @Override
    public int crear(Avion avion) {
        //INICIAR CONN, STMT, SQL. LLENAR LOS CAMPOS MARCADOS CON ?
        String sql="INSERT INTO avion (patente, numeroasientos) VALUES (?,?)";
        int idgenerado=-1;
        ResultSet rs=null;
        try(Connection conn= DatabaseUtil.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            stmt.setString(1,avion.getPatente());
            stmt.setInt(2,avion.getNumeroasientos());
            int affectedRows = stmt.executeUpdate();

            //ASIGNAR RS
            if (affectedRows > 0) {

                rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    idgenerado = rs.getInt(1);

                }
            }

        }catch (SQLException e){
            e.printStackTrace();
            log.error("Error en la conexión o la creacion del Statement.");
        }finally {
            //CERRAR RECURSOS
            if (rs!=null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error("Error al cerrar el recurso ResultSet");
                }
            }
        }
        return idgenerado;
    }
    //LOS MISMOS PATRONES DECLARADOS EN LOS COMENTARIOS SE REPITEN EN TODOS LOS METODOS,
    //ADEMAS DEL CONTENIDO PROPIO DE CADA UNO.
    @Override
    public Avion buscarPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Avion avion = null;
        try{
            conn=DatabaseUtil.getConnection();
            String sql="SELECT * FROM avion WHERE id_avion=?";
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
            if (rs.next()){
                avion=new Avion(
                        rs.getInt("id_avion"),
                        rs.getString("patente"),
                        rs.getInt("numeroasientos")
                );
            }



        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error en la conexión o la creacion del Statement.");
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error("Error al cerrar el recurso ResultSet");
                }

            }
            DatabaseUtil.closeresources(conn,stmt);
        }
        return avion;
    }

    @Override
    public List<Avion> listarTodos() {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs= null;
        List<Avion>lista_aviones=new ArrayList<>();
        try{
            conn=DatabaseUtil.getConnection();
            stmt= conn.createStatement();
            String sql="SELECT * FROM avion";
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                Avion avion=new Avion(
                        rs.getInt("id_avion"),
                        rs.getString("patente"),
                        rs.getInt("numeroasientos")
                );
                lista_aviones.add(avion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error en la conexión o la creacion del Statement.");
        }finally {
            if(rs!=null){
                try{
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error("Error al cerrar el recurso ResultSet");
                }
            }
            DatabaseUtil.closeresources(conn,stmt);
        }
        return lista_aviones;

    }

    @Override
    public boolean actualizar(Avion avion) {
     boolean actualizado=false;
     String sql="UPDATE avion SET patente=?, numeroasientos=? WHERE id_avion=?";
        try (Connection conn=DatabaseUtil.getConnection();
             PreparedStatement stmt= conn.prepareStatement(sql)){
            stmt.setString(1, avion.getPatente());
            stmt.setInt(2,avion.getNumeroasientos());
            stmt.setInt(3,avion.getId_avion());

            int affectedRows = stmt.executeUpdate();
            actualizado=(affectedRows>0);

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error en la conexión o la creacion del Statement.");
        }


     return actualizado;
    }

    @Override
    public boolean eliminar(int id) {
        Connection conn=null;
        PreparedStatement stmt=null;
        boolean realizado=false;
        try{
            conn=DatabaseUtil.getConnection();
            String sql="DELETE FROM avion WHERE id_avion=?";
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,id);
            int affectedRows=stmt.executeUpdate();
            realizado=(affectedRows>0);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error en la conexión o la creacion del Statement.");
        }finally {
            DatabaseUtil.closeresources(conn,stmt);
        }
        return realizado;
    }
}
