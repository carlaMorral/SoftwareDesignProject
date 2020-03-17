package edu.ub.ubflix.model;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    private String idSerie;
    private String titol;
    private String descripcio;
    private List<Temporada> temporades;
    private int idProductora;
    private List<Artista> artistes;

    public Serie(String idSerie, String titol, String descripcio, int p) {
        this.idSerie = idSerie;
        this.titol = titol;
        this.descripcio = descripcio;
        this.temporades = new ArrayList<>();
        this.idProductora = p;
        this.artistes = new ArrayList<>();
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public List<Temporada> getTemporades() {
        return temporades;
    }

    public void setTemporades(List<Temporada> temporades) {
        this.temporades = temporades;
    }

    public int getProductora() {
        return idProductora;
    }

    public void setProductora(int productora) {
        this.idProductora = productora;
    }

    public List<Artista> getArtistes() {
        return artistes;
    }

    public void setArtistes(List<Artista> artistes) {
        this.artistes = artistes;
    }

    public String toString(){
        String s = "Titol: ";
        s = s.concat(this.titol);
        s = s.concat("\r\n");
        s = s.concat("Descripcio: ");
        s = s.concat(this.descripcio);
        return s;
    }

    public int getNumEpisodis() {
        int numEpisodis = 0;
        for (Temporada t : temporades) {
            numEpisodis += t.getNumEpisodis();
        }
        return numEpisodis;
    }

}
