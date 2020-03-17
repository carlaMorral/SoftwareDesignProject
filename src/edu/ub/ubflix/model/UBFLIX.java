package edu.ub.ubflix.model;

import edu.ub.ubflix.controller.Controller;

import java.util.List;

public class UBFLIX {

    private CarteraClients carteraClients;
    private CatalegSeries catalegSeries;

    public UBFLIX() {
        carteraClients = new CarteraClients();
        catalegSeries = new CatalegSeries();
    }

    public void init(List<Client> allClients, List<Usuari> allUsuaris, List<Valoracio> allValoracions, List<Visualitzacio> allVisualitzacions, List<Serie> allSeries, List<Temporada> allTemporades, List<Episodi> allEpisodis) {
        carteraClients.init(allClients,allUsuaris,allValoracions,allVisualitzacions);
        catalegSeries.init(allSeries,allTemporades,allEpisodis);
    }

    public CarteraClients getCarteraClients() {
        return carteraClients;
    }

    public void setCarteraClients(CarteraClients carteraClients) {
        this.carteraClients = carteraClients;
    }

    public CatalegSeries getCatalegSeries() {
        return catalegSeries;
    }

    public void setCatalegSeries(CatalegSeries catalegSeries) {
        this.catalegSeries = catalegSeries;
    }

    public int addClient(String idClient, String pwd, String name, String dni, String address, boolean vip) {
        return carteraClients.addClient(idClient, pwd, name, dni, address, vip);
    }

    public int addUser(String idClient, String userName, int idUsuari) {
        return carteraClients.addUser(idClient, userName, idUsuari);
    }

    public List<String> listUsuaris(String idClient) {
        return carteraClients.listUsuaris(idClient);
    }

    public String veurePerfil(String idClient, int idUsuari) {
        return carteraClients.veurePerfil(idClient, idUsuari);
    }

    public List<String> llistarCatalegSeries() {
        return catalegSeries.llistarCatalegSeries();
    }

    public String mostrarDetallsSerie(String idSerie) {
        return catalegSeries.mostrarDetallsSerie(idSerie);
    }

    public List<String> listMyList(String idClient, String nomUsuari) {
        return carteraClients.listMyList(idClient, nomUsuari);
    }

    public List<String> listWatchedList(String idClient, String nomUsuari) {
        return carteraClients.listWatchedList(idClient, nomUsuari);
    }

    public List<String> listContinueWatching(String idClient, String nomUsuari) {
        return carteraClients.listContinueWatching(idClient, nomUsuari);
    }

    public boolean marcarSerieMyList(String idClient, String nomUsuari, String titolSerie) {
        return carteraClients.marcarSerieMyList(idClient, nomUsuari, catalegSeries.getSerie(titolSerie));
    }

    public void valorarEpisodi(int id, String idClient, String nomUsuari, String idSerie, String nomEpisodi, int estrelles,
                               String thumb, String data, int numTemp) {
        int idEpisodi = catalegSeries.getIdEpisodiBytitle(idSerie, numTemp, nomEpisodi);
        carteraClients.valorarEpisodi(id, idClient, nomUsuari, idSerie, idEpisodi, estrelles, thumb, data);
    }

    public void visualitzarEpisodi(int id, String idClient, String nomUsuari, String idSerie, String nomEpisodi, String estat,
                                   String data, int minutsRestants, int numTemp) {
        int idEpisodi = catalegSeries.getIdEpisodiBytitle(idSerie, numTemp, nomEpisodi);
        carteraClients.visualitzarEpisodi(id, idClient, nomUsuari, catalegSeries.getSerie(idSerie), idEpisodi,
                estat, data, minutsRestants, numTemp);
    }
    public float getDuracioEpisodi(String idSerie, int numTemporada, String idEpisodi){
        return catalegSeries.getDuracioEpisodi(idSerie,numTemporada,idEpisodi);
    }

    public boolean isEpisodiValorat(String idSerie, int numTemporada, String nomEpisodi, String idClient, String idUsuari) {
        int idEpisodi = catalegSeries.getIdEpisodiBytitle(idSerie,numTemporada,nomEpisodi);
        return carteraClients.isEpisodiValorat(idSerie,numTemporada,idEpisodi,idClient,idUsuari);
    }

    public List<String> getTemporades(String idSerie) {
        return catalegSeries.getTemporades(idSerie);
    }

    public List<Episodi> getEpisodis(String idSerie, String temporada) {
        return catalegSeries.getEpisodis(idSerie,temporada);
    }

    public String getDescripcioEpisodi(String idSerie, int numTemporada, String idEpisodi) {
        return catalegSeries.getDescripcioEpisodi(idSerie,numTemporada,idEpisodi);
    }

    public List<String> getTopTenVal(String loggedInClient, String loggedInUser) {
        return carteraClients.getTopTenVal(loggedInClient,loggedInUser);
    }

    public List<String> getTopTenVis(String loggedInClient, String loggedInUser) {
        return carteraClients.getTopTenVis(loggedInClient,loggedInUser);
    }
    public boolean validateClient(String username, String password){
        return carteraClients.validateClient(username,password);
    }

    public int getDuracioVisualitzada(String idClient, String idUsuari, Episodi episodi) {
        return carteraClients.getDuracioVisualitzada(idClient, idUsuari, episodi);
    }
}
