package edu.ub.ubflix.model;

public class Favorit {
    private int id;
    private String idClient;
    private int idUsuari;
    private String idSerie;

    public Favorit(int id, String idClient, int idUsuari, String idSerie) {
        this.id = id;
        this.idClient = idClient;
        this.idUsuari = idUsuari;
        this.idSerie = idSerie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){this.id=id;}

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }
}
