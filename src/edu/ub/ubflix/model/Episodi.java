package edu.ub.ubflix.model;

public class Episodi {
    private String idSerie;
    private int idTemporada;
    private int idEpisodi;
    private String title;
    private String descripcio;
    private String idioma;
    private String estrena;
    private String duracio;

    public Episodi(String idSerie, int idTemporada, int idEpisodi, String title, String descripcio, String idioma,
                   String estrena, String duracio) {
        this.idSerie = idSerie;
        this.idTemporada = idTemporada;
        this.idEpisodi = idEpisodi;
        this.title = title;
        this.descripcio = descripcio;
        this.idioma = idioma;
        this.estrena = estrena;
        this.duracio = duracio;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public int getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(int idTemporada) {
        this.idTemporada = idTemporada;
    }

    public void setTitol(String titol){this.title=titol;}

    public String getTitol(){return this.title;}

    public int getIdEpisodi() {
        return idEpisodi;
    }

    public void setIdEpisodi(int idEpisodi) {
        this.idEpisodi = idEpisodi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEstrena() {
        return estrena;
    }

    public void setEstrena(String estrena) {
        this.estrena = estrena;
    }

    public String getDuracio() {
        return duracio;
    }

    public void setDuracio(String duracio) {
        this.duracio = duracio;
    }

}
