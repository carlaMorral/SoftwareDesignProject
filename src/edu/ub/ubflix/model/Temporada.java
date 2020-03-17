package edu.ub.ubflix.model;

import java.util.ArrayList;
import java.util.List;

public class Temporada {
    private String idSerie;
    private int idTemporada;
    private List<Episodi> episodis;
    private int numEpisodis;

    public Temporada(String idSerie, int idTemporada, int numEpisodis) {
        this.idSerie = idSerie;
        this.idTemporada = idTemporada;
        this.episodis = new ArrayList<>();
        this.numEpisodis = numEpisodis;
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

    public List<Episodi> getEpisodis() {
        return episodis;
    }

    public void setEpisodis(List<Episodi> episodis) {
        this.episodis = episodis;
    }

    public int getNumEpisodis() {
        return this.numEpisodis;
    }


}
