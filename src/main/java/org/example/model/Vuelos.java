package org.example.model;

public class Vuelos {
    //ATRIBUTOS
    private int id;
    private String origen;
    private String destino;
    private int numeroavion;
    private int tiempominutos;
    //CONSTRUCTOR VACÍO
    public Vuelos() {
    }
   //CONSTRUCTOR SIN ID
    public Vuelos(String origen, String destino, int numeroavion, int tiempominutos) {
        this.origen = origen;
        this.destino = destino;
        this.numeroavion = numeroavion;
        this.tiempominutos = tiempominutos;
    }
   //CONSTRUCTOR COMPLETO
    public Vuelos(int id, String origen, String destino, int numeroavion, int tiempominutos) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.numeroavion = numeroavion;
        this.tiempominutos = tiempominutos;
    }
//GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getNumeroavion() {
        return numeroavion;
    }

    public void setNumeroavion(int numeroavion) {
        this.numeroavion = numeroavion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getTiempominutos() {
        return tiempominutos;
    }

    public void setTiempominutos(int tiempominutos) {
        this.tiempominutos = tiempominutos;
    }
    //METODO toString QUE MUESTRA COHERENTEMENTE LA INFORMACION DE CADA VUELO
    @Override
    public String toString() {
        String respuesta="Id del vuelo: "+id +"\n"+
        "Origen del vuelo: "+origen +"\n"+
        "Destino del vuelo: "+destino +"\n"+
        "Numero de avion del vuelo: "+numeroavion +"\n"+
        "Tiempo (en minutos) del vuelo: "+tiempominutos;
        return respuesta;
    }
}
