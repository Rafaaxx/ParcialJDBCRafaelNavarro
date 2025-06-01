package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    //INTRODUCCION DE DATOS PARA LA BASE
    private static final String URL="jdbc:h2:~/test";
    private static final String Usuario="Rafael Navarro";
    private static final String Contrasena="solrac3690";
    private static final Logger log = LogManager.getLogger(DatabaseUtil.class);

    //VINCULAR LA VARIABLE CONNECTION A LA BASE DE DATOS USANDO LA INFORMACION
    //PREVIAMENTE INGRESADA
    public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(URL,Usuario,Contrasena);
    }
    //METODO PARA CERRAR TANTO LA CONEXION COMO EL STATEMENT CREADO PARA LA CONSULTA.
    public static void closeresources(Connection conn, Statement stmt){
        try{
            if(stmt!=null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
           log.error("Error al cerrar el statement");
        }
        try{
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error al cerrar la conexiones");
        }

    }
    //INICIAR LA BASE DE DATOS DE VUELOS CON EL COMANDO CREATE
    public static void iniciarbd(){
    Connection conn=null;
    Statement stmt=null;
    try{
        conn=getConnection();
        stmt=conn.createStatement();
        String sql= "CREATE TABLE IF NOT EXISTS vuelos (" +

                "id INT AUTO_INCREMENT PRIMARY KEY, " +

                "origen VARCHAR(100) NOT NULL, " +

                "destino VARCHAR(255) NOT NULL, " +

                "numeroavion INT NOT NULL, " +
                "tiempominutos INT NOT NULL)";
        stmt.execute(sql);
        log.info("Base de datos iniciada.");
    } catch (SQLException e) {
        e.printStackTrace();
        log.error("Error al iniciar la base de datos.");
    }finally {
        closeresources(conn,stmt);
    }
    }
    //INICIAR LA BASE DE DATOS DE AVION CON EL COMANDO CREATE
    public static void iniciarbdavion(){
        Connection conn=null;
        Statement stmt=null;
        try{
            conn=getConnection();
            stmt=conn.createStatement();
            String sql= "CREATE TABLE IF NOT EXISTS avion (" +

                    "id_avion INT AUTO_INCREMENT PRIMARY KEY, " +

                    "patente VARCHAR(100) NOT NULL, " +

                    "numeroasientos INT NOT NULL)";
            stmt.execute(sql);
            log.info("Base de datos de avion iniciada.");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error al iniciar la base de datos de avion.");
        }finally {
            closeresources(conn,stmt);
        }
    }
    }
