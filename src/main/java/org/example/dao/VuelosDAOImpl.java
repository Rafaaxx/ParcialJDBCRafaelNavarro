package org.example.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.util.DatabaseUtil;
import org.example.model.Vuelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VuelosDAOImpl implements VuelosDAO<Vuelos> {
    private static final Logger log = LogManager.getLogger(VuelosDAOImpl.class);

    @Override
    public int crear(Vuelos vuelo) {
        //CONEXION, STATEMENT Y RESULTSET
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idgenerado = -1;
        try {
            //INICIAR CONN, STMT, SQL. LLENAR LOS CAMPOS MARCADOS CON ?
            conn = DatabaseUtil.getConnection();
            String sql = "INSERT INTO vuelos (origen, destino, numeroavion, tiempominutos) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, vuelo.getOrigen());
            stmt.setString(2, vuelo.getDestino());
            stmt.setInt(3, vuelo.getNumeroavion());
            stmt.setInt(4, vuelo.getTiempominutos());
            int affectedRows = stmt.executeUpdate();

              //ASIGNAR RS
            if (affectedRows > 0) {

                rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    idgenerado = rs.getInt(1);

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error en la conexión o la creacion del Statement.");
        } finally {
            //CERRAR RECURSOS
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error("Error al cerrar el recurso ResultSet");
                }

            }
            DatabaseUtil.closeresources(conn, stmt);
        }

        return idgenerado;
    }
    //LOS MISMOS PATRONES DECLARADOS EN LOS COMENTARIOS SE REPITEN EN TODOS LOS METODOS,
    //ADEMAS DEL CONTENIDO PROPIO DE CADA UNO.

    @Override
    public Vuelos buscarPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vuelos vuelo = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM vuelos WHERE id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                vuelo = new Vuelos(
                        rs.getInt("id"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getInt("numeroavion"),
                        rs.getInt("tiempominutos")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error en la conexión o la creacion del Statement.");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error("Error al cerrar el recurso ResultSet");
                }
            }
            DatabaseUtil.closeresources(conn, stmt);
        }
        return vuelo;
    }

    @Override
    public List<Vuelos> listarTodos() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Vuelos> listavuelos = new ArrayList<>();
        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM vuelos";
            rs=stmt.executeQuery(sql);
            while (rs.next()) {
                Vuelos vuelo = new Vuelos(
                        rs.getInt("id"),
                        rs.getString("origen"),
                        rs.getString("destino"),
                        rs.getInt("numeroavion"),
                        rs.getInt("tiempominutos")
                );
                listavuelos.add(vuelo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error en la conexión o la creacion del Statement.");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.error("Error al cerrar el recurso ResultSet");
                }

            }
            DatabaseUtil.closeresources(conn, stmt);
        }

        return listavuelos;
    }

    @Override
    public boolean actualizar(Vuelos vuelo) {
        boolean actualizado = false;
        String sql = "UPDATE vuelos SET origen=?, destino=?, numeroavion=?, tiempominutos=? WHERE id=?";
        try (Connection conn=DatabaseUtil.getConnection();
             PreparedStatement stmt= conn.prepareStatement(sql)){
            stmt.setString(1, vuelo.getOrigen());
            stmt.setString(2, vuelo.getDestino());
            stmt.setInt(3, vuelo.getNumeroavion());
            stmt.setInt(4, vuelo.getTiempominutos());
            stmt.setInt(5, vuelo.getId());

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
            String sql="DELETE  FROM vuelos WHERE id=?";
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

