package edu.ub.ubflix.model;

public class Visualitzacio {
    private static final String ESTAT_WATCHING = "Watching";
    private static final String ESTAT_WATCHED = "Watched";
    private int id;
    private int idUsuari;
    private String idSerie;
    private int numTemporada;
    private int idEpisodi;
    private String estat;
    private String data;
    private int segonsRestants;

    public Visualitzacio(int id, int idUsuari, String idSerie, int numTemp, int idEpisodi,
                         String data, int segonsRestants) {
        this.id = id;
        this.idUsuari = idUsuari;
        this.idSerie = idSerie;
        this.numTemporada = numTemp;
        this.idEpisodi = idEpisodi;
        this.data = data;
        this.segonsRestants = segonsRestants;
        if (segonsRestants==0) this.estat = ESTAT_WATCHED;
        else this.estat=ESTAT_WATCHING;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumTemporada() {
        return numTemporada;
    }

    public void setNumTemporada(int numTemporada) {
        this.numTemporada = numTemporada;
    }

    public int getIdEpisodi() {
        return idEpisodi;
    }

    public void setIdEpisodi(int idEpisodi) {
        this.idEpisodi = idEpisodi;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isBeingWatched() {
        return estat.equals(ESTAT_WATCHING);
    }

    public boolean isWatched() {
        return estat.equals(ESTAT_WATCHED);
    }

    public int getSegonsRestants() {
        return segonsRestants;
    }

    public void setSegonsRestants(int segonsRestants) {
        this.segonsRestants = segonsRestants;}
}
