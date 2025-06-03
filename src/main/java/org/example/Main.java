package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.AvionDAOImpl;
import org.example.dao.VuelosDAO;
import org.example.dao.VuelosDAOImpl;
import org.example.model.Avion;
import org.example.model.Vuelos;
import org.example.util.DatabaseUtil;

import javax.swing.*;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        DatabaseUtil.iniciarbd();
        VuelosDAO<Vuelos> vuelosDAO=new VuelosDAOImpl();
        log.info("Operaciones: ");
        //LOS try-catch SE UTILIZAN PARA VALIDAR EL DATO INGRESADO POR EL USUARIO
        // EN LA MAYORIA DE OPERACIONES
        boolean terminado=false;
        while (terminado==false) {
            int numeroeleccion = Integer.parseInt(JOptionPane.showInputDialog("Diga que operación desea realizar: \n" +
                    "1-Crear 2 vuelos \n" +
                    "2-Leer vuelos \n" +
                    "3-Buscar por ID \n" +
                    "4-Actualizar un vuelo \n" +
                    "5-Eliminar un vuelo \n" +
                    "6-Terminar"));
            switch (numeroeleccion) {
                case 1:
                    //CREAR VUELOS


                    try {
                        String origenvuelo1 = JOptionPane.showInputDialog("Diga el origen del vuelo 1:");
                        String destinovuelo1 = JOptionPane.showInputDialog("Diga el destino del vuelo 1:");
                        int numeroavionvuelo1 = Integer.parseInt(JOptionPane.showInputDialog("Diga el numero del avion del vuelo 1:"));
                        int tiempominutosavion1 = Integer.parseInt(JOptionPane.showInputDialog("Diga el tiempo en minutos del vuelo 1:"));
                        if (origenvuelo1 == null || destinovuelo1 == null || numeroavionvuelo1 <= 0 || tiempominutosavion1 <= 0
                                || origenvuelo1.isEmpty() || destinovuelo1.isEmpty()) {
                            throw new IllegalArgumentException();
                        }
                        Vuelos vuelo = new Vuelos(origenvuelo1, destinovuelo1, numeroavionvuelo1, tiempominutosavion1);
                        int idgenerado = vuelosDAO.crear(vuelo);
                        log.info("Id del nuevo vuelo: " + idgenerado);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        log.error("Los campos estàn vacìos o el dato no corresponde.");

                    }
                    try {
                        String origenvuelo2 = JOptionPane.showInputDialog("Diga el origen del vuelo 2:");
                        String destinovuelo2 = JOptionPane.showInputDialog("Diga el destino del vuelo 2:");
                        int numeroavionvuelo2 = Integer.parseInt(JOptionPane.showInputDialog("Diga el numero del avion del vuelo 2:"));
                        int tiempominutosavion2 = Integer.parseInt(JOptionPane.showInputDialog("Diga el tiempo en minutos del vuelo 2:"));
                        if (origenvuelo2 == null || destinovuelo2 == null || numeroavionvuelo2 <= 0 || tiempominutosavion2 <= 0
                                || origenvuelo2.isEmpty() || destinovuelo2.isEmpty()) {
                            throw new IllegalArgumentException();
                        }
                        Vuelos vuelo2 = new Vuelos(origenvuelo2, destinovuelo2, numeroavionvuelo2, tiempominutosavion2);
                        int idgenerado2 = vuelosDAO.crear(vuelo2);
                        log.info("Id del nuevo vuelo: " + idgenerado2);

                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        log.error("Los campos estàn vacìos o los tipos de datos no corresponde.");
                    }
                    break;

                case 2:
                    //LEER TODOS LOS VUELOS
                    log.info("Lista de todos los vuelos: ");
                    List<Vuelos> listadevuelos = vuelosDAO.listarTodos();
                    for (int i = 0; i < listadevuelos.size(); i++) {
                        log.info(listadevuelos.get(i).toString());
                    }
                    break;
                case 3:
                    //BUSCAR UN VUELO POR SU ID
                    try {
                        int idparabuscar = Integer.parseInt(JOptionPane.showInputDialog("Diga el id del vuelo que desea buscar: "));
                        if (idparabuscar <= 0) {
                            throw new IllegalArgumentException();
                        }
                        Vuelos vueloparabuscar = vuelosDAO.buscarPorId(idparabuscar);
                        if(vueloparabuscar!=null){
                            log.info(vueloparabuscar.toString());
                        }
                        else {
                            log.warn("No se encontró el vuelo con el id ingresado.");
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        log.error("El campo esta vacio o no corresponde el tipo de dato.");

                    }
                    break;
                case 4:
                    //ACTUALIZAR UN VUELO
                    try {
                        int idparaactualizar = Integer.parseInt(JOptionPane.showInputDialog("Diga el id del vuelo que desea actualizar: "));
                        if (idparaactualizar <= 0) {
                            throw new IllegalArgumentException();
                        }
                        Vuelos vueloparabuscaryactualizar = vuelosDAO.buscarPorId(idparaactualizar);
                        String origennuevo = JOptionPane.showInputDialog("Diga el nuevo origen: ");
                        String destinonuevo = JOptionPane.showInputDialog("Diga el nuevo destino: ");
                        int numeroavionnuevo = Integer.parseInt(JOptionPane.showInputDialog("Diga el nuevo numero del avion: "));
                        int tiempominutosnuevo = Integer.parseInt(JOptionPane.showInputDialog("Diga el nuevo tiempo (en minutos): "));
                        if (destinonuevo == null || destinonuevo.isEmpty() || origennuevo == null || origennuevo.isEmpty() || numeroavionnuevo <= 0 || tiempominutosnuevo <= 0) {
                            throw new IllegalArgumentException();
                        }
                        vueloparabuscaryactualizar.setDestino(destinonuevo);
                        vueloparabuscaryactualizar.setNumeroavion(numeroavionnuevo);
                        vueloparabuscaryactualizar.setOrigen(origennuevo);
                        vueloparabuscaryactualizar.setTiempominutos(tiempominutosnuevo);
                        boolean actualizado = vuelosDAO.actualizar(vueloparabuscaryactualizar);
                        log.info("Vuelo actualizado: " + vueloparabuscaryactualizar.toString());
                        log.info("Actualizado: " + actualizado);
                        //VERIFICAR ACTUALIZACIÓN
                        Vuelos vueloactualizado = vuelosDAO.buscarPorId(idparaactualizar);
                        log.info("Verificacion del vuelo: " + vueloactualizado.toString());
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        log.error("El campo esta vacio o no corresponde el tipo de dato.");
                    }
                  break;
                case 5:
                    //ELIMINAR VUELO
                    try {
                        int idparaeliminar = Integer.parseInt(JOptionPane.showInputDialog("Diga el id del vuelo que desea eliminar: "));
                        if (idparaeliminar <= 0) {
                            throw new IllegalArgumentException();
                        }
                        boolean eliminado = vuelosDAO.eliminar(idparaeliminar);
                        if (eliminado) {
                            log.info("Se elimino el vuelo, si es que existìa previamente en la tabla.");
                        }
                        //LISTA NUEVA DESPUÉS DE LA ELIMINACIÓN
                        List<Vuelos> listadevuelosnueva = vuelosDAO.listarTodos();
                        log.info("Lista de vuelos nueva: ");
                        for (int i = 0; i < listadevuelosnueva.size(); i++) {
                            log.info(listadevuelosnueva.get(i).toString());
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        log.error("El campo esta vacio o no corresponde el tipo de dato.");
                    }
                    break;
                case 6:
                    terminado = true;
                    break;
                default:
                    System.out.println("Numero no valido");
                    break;
            }
        }
        //REPETICIÓN DEL MISMO PROCESO PARA AVIONES
        DatabaseUtil.iniciarbdavion();
        VuelosDAO<Avion>avionDAO=new AvionDAOImpl();
        log.info("Operaciones: ");
        boolean terminado2=false;
        while (terminado2==false){
            int numeroeleccion2 = Integer.parseInt(JOptionPane.showInputDialog("Diga que operación desea realizar: \n" +
                    "1-Crear 2 aviones \n" +
                    "2-Leer aviones \n" +
                    "3-Buscar por ID \n" +
                    "4-Actualizar un avion \n" +
                    "5-Eliminar un avion \n" +
                    "6-Terminar"));
            switch (numeroeleccion2){
                case 1:
                    //CREAR AVION
                    try {
                        String patenteavion1 = JOptionPane.showInputDialog("Diga la patente del avion numero 1.");
                        int numeroasientosavion1 = Integer.parseInt(JOptionPane.showInputDialog("Diga la cantidad de asientos del avion numero 1."));
                        if(patenteavion1==null || patenteavion1.isEmpty()|| numeroasientosavion1<=0){
                            throw new IllegalArgumentException();
                        }
                        Avion avion = new Avion(patenteavion1, numeroasientosavion1);
                        int idgeneradoavion = avionDAO.crear(avion);
                        log.info("Id del nuevo avion: " + idgeneradoavion);

                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                        log.error("Los campos estàn vacìos o los tipos de datos no corresponde.");
                    }
                    try {
                        String patenteavion2 = JOptionPane.showInputDialog("Diga la patente del avion numero 2.");
                        int numeroasientosavion2 = Integer.parseInt(JOptionPane.showInputDialog("Diga la cantidad de asientos del avion numero 2."));
                        if(patenteavion2==null || patenteavion2.isEmpty()||numeroasientosavion2<=0){
                            throw new IllegalArgumentException();
                        }
                        Avion avion2 = new Avion(patenteavion2, numeroasientosavion2);
                        int idgeneradoavion2 = avionDAO.crear(avion2);
                        log.info("Id del nuevo avion: " + idgeneradoavion2);
                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                        log.error("Los campos estàn vacìos o los tipos de datos no corresponde.");
                    }
                    break;
                case 2:
                    //LEER TODOS LOS AVIONES
                    log.info("Lista de todos los aviones: ");
                    List<Avion>listadeaviones=avionDAO.listarTodos();
                    for (int i = 0; i < listadeaviones.size(); i++) {
                        log.info(listadeaviones.get(i).toString());
                    }
                    break;
                case 3:
                    //BUSCAR UN AVION POR SU ID
                    try {
                        int idparabuscaravion = Integer.parseInt(JOptionPane.showInputDialog("Diga el id del avion que desea buscar: "));
                        if (idparabuscaravion <= 0) {
                            throw new IllegalArgumentException();
                        }
                        Avion avionbuscado = avionDAO.buscarPorId(idparabuscaravion);
                        if(avionbuscado!=null){
                            log.info(avionbuscado.toString());
                        }
                        else {
                            log.warn("No se encontro un avion con el id solicitado.");
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        log.error("El campo esta vacio o no corresponde el tipo de dato.");
                    }
                    break;
                case 4:
                    //ACTUALIZAR UN AVION
                    try {
                        int idparaactualizaravion = Integer.parseInt(JOptionPane.showInputDialog("Diga el id del avion que desea actualizar: "));
                        if(idparaactualizaravion<=0){
                            throw new IllegalArgumentException();
                        }
                        Avion avionparabuscaryactualizar = avionDAO.buscarPorId(idparaactualizaravion);
                        String patentenueva = JOptionPane.showInputDialog("Diga la nueva patente: ");
                        int numeroasientosnuevo=Integer.parseInt(JOptionPane.showInputDialog("Diga la cantidad de asientos nueva:"));
                        if(patentenueva==null ||patentenueva.isEmpty() || numeroasientosnuevo<=0){
                            throw new IllegalArgumentException();
                        }
                        avionparabuscaryactualizar.setPatente(patentenueva);
                        avionparabuscaryactualizar.setNumeroasientos(numeroasientosnuevo);
                        boolean actualizadoavion = avionDAO.actualizar(avionparabuscaryactualizar);
                        log.info("Avion actualizado: " + avionparabuscaryactualizar.toString());
                        log.info("Actualizado: " + actualizadoavion);

                        //VERIFICAR LA ACTUALIZACIÓN
                        Avion avionactualizado = avionDAO.buscarPorId(idparaactualizaravion);
                        log.info("Verificacion del avion: " + avionactualizado.toString());
                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                        log.error("El campo esta vacio o no corresponde el tipo de dato.");
                    }

                    break;
                case 5:
                    //ELIMINAR UN AVION
                    try {
                        int idparaeliminaravion = Integer.parseInt(JOptionPane.showInputDialog("Diga el id del avion que desea eliminar: "));
                        if (idparaeliminaravion<=0){
                            throw new IllegalArgumentException();
                        }
                        boolean avioneliminado = avionDAO.eliminar(idparaeliminaravion);
                        if (avioneliminado) {
                            log.info("Se elimino el avion, si es que existìa previamente en la tabla.");
                        }
                        //LISTA NUEVA DESPUÉS DE LA ELIMINACIÓN
                        List<Avion> listadeavionesnueva = avionDAO.listarTodos();
                        log.info("Lista de aviones nueva: ");
                        for (int i = 0; i < listadeavionesnueva.size(); i++) {
                            log.info(listadeavionesnueva.get(i).toString());
                        }
                    }catch (IllegalArgumentException e){
                        e.printStackTrace();
                        log.error("El campo esta vacio o no corresponde el tipo de dato.");
                    }
                    break;
                case 6:
                    terminado2=true;
                    break;
                default:
                    System.out.println("El numero no es valido");
                    break;
            }
        }
    }
    }
