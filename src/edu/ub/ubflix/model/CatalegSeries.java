package edu.ub.ubflix.model;

import edu.ub.ubflix.controller.Controller;

import java.util.*;

public class CatalegSeries {

    private Map<String, Serie> series;

    public CatalegSeries() {
        this.series = new HashMap<>();
    }

    public void init(List<Serie> allSeries, List<Temporada> allTemporades, List<Episodi> allEpisodis) {

        for (Serie s : allSeries) {
            List<Temporada> belongingTemp = new ArrayList<>();
            for(Temporada t: allTemporades) {
                List<Episodi> belonginEp = new ArrayList<>();
                if(t.getIdSerie().equals(s.getIdSerie())){
                    for(Episodi e: allEpisodis){
                        if(t.getIdTemporada()==e.getIdTemporada() && e.getIdSerie().equals(t.getIdSerie())) belonginEp.add(e);
                    }
                    t.setEpisodis(belonginEp);
                    belongingTemp.add(t);
                }

            }
            s.setTemporades(belongingTemp);
            series.put(s.getIdSerie(), s);
        }
    }

    public Serie getSerie(String titolSerie) {
        for(String id : series.keySet()){
            if(series.get(id).getTitol().equals(titolSerie)) return Objects.requireNonNull(series.get(id),"Serie not found");
        }
        return Objects.requireNonNull(series.get(titolSerie), "Serie not found");
    }

    public String mostrarDetallsSerie(String idSerie) {
        return getSerie(idSerie).toString();
    }

    public List<String> llistarCatalegSeries() {
        List<String> titols = new ArrayList<>();
        for (Serie s : series.values()) {
            titols.add(s.getTitol());
        }
        Collections.sort(titols);
        return titols;
    }

    public float getDuracioEpisodi(String idSerie, int numTemporada, String idEpisodi) {
        Serie s = this.getSerie(idSerie);
        List<Temporada> temporades = s.getTemporades();
        Temporada t = temporades.get(numTemporada);
        List<Episodi> episodis  = t.getEpisodis();
        for(Episodi e: episodis){
            if(e.getTitol().equals(idEpisodi)){
                return Float.parseFloat(e.getDuracio());
            }
        }
        return 0;
    }

    public List<String> getTemporades(String idSerie) {
        List<Temporada> temps= this.getSerie(idSerie).getTemporades();
        List<String> res = new ArrayList<>();
        for(Temporada t: temps){
            res.add("Temporada "+t.getIdTemporada());
        }
        return res;

    }

    public int getIdEpisodiBytitle(String idSerie, int numTemporada, String titol){
        Serie s = this.getSerie(idSerie);
        List<Temporada> temporades = s.getTemporades();
        Temporada t = temporades.get(numTemporada-1);
        List<Episodi> episodis  = t.getEpisodis();
        for(Episodi e: episodis){
            if (e.getTitol().equals(titol)) return e.getIdEpisodi();
        }
        return 0;
    }

    public List<Episodi> getEpisodis(String idSerie, String temporada) {
        List<Temporada> temps= this.getSerie(idSerie).getTemporades();
        List<Episodi> res = new ArrayList<>();
        for(Temporada t: temps){
            if(Integer.toString(t.getIdTemporada()).equals(temporada)) {
                res.addAll(t.getEpisodis());
            }
        }
        return res;
    }

    public String getDescripcioEpisodi(String idSerie, int numTemporada, String idEpisodi) {
        Serie s = this.getSerie(idSerie);
        List<Temporada> temporades = s.getTemporades();
        Temporada t = temporades.get(numTemporada-1);
        List<Episodi> episodis  = t.getEpisodis();
        for(Episodi e: episodis){
            if(e.getTitol().equals(idEpisodi)){
                return e.getDescripcio();
            }
        }
        return "Descripció no disponible";
    }
}


