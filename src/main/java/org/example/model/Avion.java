package org.example.model;

public class Avion {
    //ATRIBUTOS
    private int id_avion;
    private String patente;
    private int numeroasientos;
    //METODO toString QUE MUESTRA COHERENTEMENTE LA INFORMACION DE CADA VUELO
    @Override
    public String toString() {
        String respuesta="Id del avion: "+id_avion+ "\n" +
                "Patente del avion: "+patente+ "\n" +
                "Numero de asientos: "+numeroasientos;

        return respuesta;
    }
    //CONSTRUCTOR VACÍO
    public Avion() {
    }
    //CONSTRUCTOR SIN ID
    public Avion(String patente, int numeroasientos) {
        this.patente = patente;
        this.numeroasientos = numeroasientos;
    }
    //CONSTRUCTOR COMPLETO
    public Avion(int id_avion, String patente, int numeroasientos) {
        this.id_avion = id_avion;
        this.patente = patente;
        this.numeroasientos = numeroasientos;
    }
    //GETTERS AND SETTERS
    public int getId_avion() {
        return id_avion;
    }

    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getNumeroasientos() {
        return numeroasientos;
    }

    public void setNumeroasientos(int numeroasientos) {
        this.numeroasientos = numeroasientos;
    }
}
