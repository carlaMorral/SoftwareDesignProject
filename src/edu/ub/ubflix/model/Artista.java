package edu.ub.ubflix.model;

public class Artista {
    private int id;
    private String idSerie;
    private String nom;
    private String nacionalitat;
    private String tipus;

    public Artista(int id, String idSerie, String nom, String nacionalitat, String tipus) {
        this.id = id;
        this.idSerie = idSerie;
        this.nom = nom;
        this.nacionalitat = nacionalitat;
        this.tipus = tipus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNacionalitat() {
        return nacionalitat;
    }

    public void setNacionalitat(String nacionalitat) {
        this.nacionalitat = nacionalitat;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
