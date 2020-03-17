package edu.ub.ubflix.model;

import edu.ub.ubflix.model.llistarTopSeries.llistarTop;
import edu.ub.ubflix.model.llistarTopSeries.llistarTopValoracions;
import edu.ub.ubflix.model.llistarTopSeries.llistarTopVisualitzacions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuari {
    private String idClient;
    private int idUsuari;
    private String name;
    private List<Serie> myList;
    private List<Serie> watchedList;
    private List<Serie> continueWatching;
    private List<Visualitzacio> visualitzacions;
    private List<Valoracio> valoracions;
    private llistarTop logicaTop;

    public Usuari(String idClient, int idUsuari, String name){
        this.idClient = idClient;
        this.idUsuari = idUsuari;
        this.name = name;
        this.myList = new ArrayList<>();
        this.watchedList = new ArrayList<>();
        this.continueWatching = new ArrayList<>();
        this.visualitzacions = new ArrayList<>();
        this.valoracions = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Serie> getMyList() {
        return myList;
    }

    public void setMyList(List<Serie> myList) {
        this.myList = myList;
    }

    public List<Serie> getWatchedList() {
        return watchedList;
    }

    public void setWatchedList(List<Serie> watchedList) {
        this.watchedList = watchedList;
    }

    public List<Serie> getContinueWatching() {
        return continueWatching;
    }

    public void setContinueWatching(List<Serie> continueWatching) {
        this.continueWatching = continueWatching;
    }

    public List<Visualitzacio> getVisualitzacions() {
        return visualitzacions;
    }

    public void setVisualitzacions(List<Visualitzacio> visualitzacions) {
        this.visualitzacions = visualitzacions;
    }

    public List<Valoracio> getValoracions() {
        return valoracions;
    }

    public void setValoracions(List<Valoracio> valoracions) {
        this.valoracions = valoracions;
    }

    private List<String> listSeries(List<Serie> l) {
        List<String> titols = new ArrayList<>();
        for (Serie s : l) {
            titols.add(s.getTitol());
        }
        Collections.sort(titols);
        return titols;
    }

    public boolean marcarSerieMyList(Serie s) {
        if (myList.contains(s)) return false;
        return myList.add(s);
    }

    public List<String> listMyList() {
        return listSeries(myList);
    }

    public List<String> listWatchedList() {
        return listSeries(watchedList);
    }

    public List<String> listContinueWatching() {
        return listSeries(continueWatching);
    }

    public boolean valorarEpisodi(int id, String idSerie, int idEpisodi, int estrelles, String thumb, String data) {
        Valoracio novaValoracio = new Valoracio(id, idClient, idUsuari, idSerie, idEpisodi, estrelles, thumb, data);
        for (Valoracio v : valoracions) {
            if (v.matchesIds(novaValoracio)) {
                return v.updateRating(estrelles, thumb, data);
            }
        }
        return valoracions.add(novaValoracio);
    }

    public String visualitzarEpisodi(int id, Serie serie, int idEpisodi, String estat, String data, int segonsRestants, int numTemp) {

        Visualitzacio v = (Visualitzacio) searchVisualitzacio(serie.getIdSerie(), idEpisodi)[0];
        int episodisVist = (int) searchVisualitzacio(serie.getIdSerie(), idEpisodi)[1];
        if (v != null) {
            v.setEstat(estat);
            v.setData(data);
            v.setSegonsRestants(segonsRestants);
        } else {
            v = new Visualitzacio(id, idUsuari, serie.getTitol(), numTemp, idEpisodi, data, segonsRestants);
            visualitzacions.add(v);
        }
        if (v.getEstat().equals("Watched")) episodisVist++;
        if (serie.getNumEpisodis() == episodisVist) {
            watchedList.add(serie);
            continueWatching.remove(serie);
        } else if (!continueWatching.contains(serie)) {
            continueWatching.add(serie);
        }
        return estat;
    }

    private Object[] searchVisualitzacio(String idSerie, int idEpisodi) {
        Visualitzacio vis = null;
        int episodisVist = 0;
        for (Visualitzacio v : visualitzacions) {
            if (v.getIdSerie().equals(idSerie) && v.getIdEpisodi()==idEpisodi) {
                vis = v;
            } else if (v.getIdSerie().equals(idSerie) && v.getEstat().equals("Watched")) {
                episodisVist++;
            }
        }
        return new Object[]{vis, episodisVist};
    }

    public String toString() {
        String s = "Id client: ";
        s = s.concat(this.idClient);
        s = s.concat("\r\n");
        s = s.concat("Nom usuari: ");
        s = s.concat(this.name);
        return s;
    }

    public boolean isEpisodiValorat(String idSerie, int idEpisodi) {
        for(Valoracio v: valoracions){
            if(v.getIdSerie().equals(idSerie) && v.getIdEpisodi()==idEpisodi){
                return true;
            }
        }
        return false;
    }

    public int getSegonsRestants(String idSerie, int idEpisodi) {
        Visualitzacio lastVisualization = null;
        for(Visualitzacio v: this.visualitzacions){
            if(v.getIdSerie().equals(idSerie) && v.getIdEpisodi()==idEpisodi){
                lastVisualization = v;
            }
        }
        if(lastVisualization!=null) return lastVisualization.getSegonsRestants();
        return 0;
    }


    public List<String> getTopTenVal() {
        this.logicaTop = new llistarTopValoracions();
        return ((llistarTopValoracions)logicaTop).getTop(valoracions);
    }

    public List<String> getTopTenVis() {
        this.logicaTop = new llistarTopVisualitzacions();
        return ((llistarTopVisualitzacions)logicaTop).getTop(this.getVisualitzacions());
    }


    public int getDuracioVisualitzada(Episodi episodi) {
        String duracio = episodi.getDuracio();
        int duracio_segons = (Integer.parseInt(duracio.split(":")[0])*60)+Integer.parseInt(duracio.split(":")[1]);
        return duracio_segons-getSegonsRestants(episodi.getIdSerie(), episodi.getIdEpisodi());
    }
}
