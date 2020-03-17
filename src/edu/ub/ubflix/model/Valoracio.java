package edu.ub.ubflix.model;

public class Valoracio {

    private int id;
    private String idClient;
    private int idUsuari;
    private String idSerie;
    private int idEpisodi;
    private int estrelles;
    private String thumb;
    private String data;

    public Valoracio(int id, String idClient, int idUsuari, String idSerie, int idEpisodi, int estrelles, String thumb, String data) {
        this.id = id;
        this.idClient = idClient;
        this.idUsuari = idUsuari;
        this.idSerie = idSerie;
        this.idEpisodi = idEpisodi;
        this.estrelles = estrelles;
        this.thumb = thumb;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getIdEpisodi() {
        return idEpisodi;
    }

    public void setIdEpisodi(int idEpisodi) {
        this.idEpisodi = idEpisodi;
    }

    public int getEstrelles() {
        return estrelles;
    }

    public void setEstrelles(int estrelles) {
        this.estrelles = estrelles;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean matchesIds(Valoracio v) {
        return idClient.equals(v.getIdClient()) && idUsuari==v.getIdUsuari() && idSerie.equals(v.getIdSerie())
                && idEpisodi==v.getIdEpisodi();
    }

    public boolean updateRating(int estrelles, String thumb, String data) {
        this.estrelles = estrelles;
        this.thumb = thumb;
        this.data = data;
        return true;
    }
}
