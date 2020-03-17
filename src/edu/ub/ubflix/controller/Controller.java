package edu.ub.ubflix.controller;

import edu.ub.ubflix.model.*;
import edu.ub.ubflix.resources.service.AbstractFactoryData;
import edu.ub.ubflix.resources.service.DataService;
import edu.ub.ubflix.resources.service.FactorySQL;
import edu.ub.ubflix.view.FormUBFLIX;

import java.util.List;

public class Controller {
    private volatile static Controller controller;
    private DataService dataService;
    private UBFLIX model;
    private FormUBFLIX view;

    private Controller(String name) {
        if (name.equals("SQL")) {
            AbstractFactoryData factory = new FactorySQL();
            dataService = new DataService(factory);
            model = new UBFLIX();
            view = new FormUBFLIX();
        }
    }

    public static Controller getInstance() {
        if (controller == null) {
            synchronized (Controller.class) {
                if (controller == null) {
                    controller = new Controller("SQL");
                }
            }
        }
        return controller;
    }

    public void init() throws Exception {
        model.init(dataService.getAllClients(),dataService.getAllUsuaris(),dataService.getAllValoracions(),
                dataService.getAllVisualitzacions(), dataService.getAllSeries(),
                dataService.getAllTemporades(),dataService.getAllEpisodis());
        view.init();
    }

    // CLIENTS

    public boolean validateClient(String username, String password) {
        boolean isInDatabase = dataService.getClientByUsuariAndPassword(username,password) != null;
        boolean isInModel = model.validateClient(username,password);
        return (isInDatabase || isInModel);
    }

    public String addClient(String idClient, String pwd, String name, String dni, String address, boolean vip) {
        int result = model.addClient(idClient, pwd, name, dni, address, vip);
        switch(result) {
            case -1: return "Taken username";
            case -2: return "Password not secure enough";
            case -3: return "Invalid dni";
            case -4: return "Dni already taken";
        }

        // Data persistence layer dealing
        Client c = model.getCarteraClients().getClient(idClient);
        try {
            dataService.addClient(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Client created";
    }

    public String addUser(String idClient, String userName, int idUsuari) {
        int result = model.addUser(idClient, userName, idUsuari);
        switch(result) {
            case -1: return "Max number of users already achieved";
            case -2: return "Name already taken";
        }

        // Data persistence layer dealing
        Usuari u = model.getCarteraClients().getClient(idClient).getUsuari(userName);
        try {
            dataService.addUsuari(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "User created";
    }

    public List<String> listUsuaris(String idClient) {
        return model.listUsuaris(idClient);
    }

    public String veurePerfil(String idClient, int idUsuari) {
        return model.veurePerfil(idClient, idUsuari);
    }

    // SERIES

    public List<String> llistarCatalegSeries() {
        return model.llistarCatalegSeries();
    }

    public String mostrarDetallsSerie(String idSerie) {
        return model.mostrarDetallsSerie(idSerie);
    }

    public List<String> listMyList(String nomClient, String nomUsuari) {
        return model.listMyList(nomClient, nomUsuari);
    }

    public List<String> listWatchedList(String nomClient, String nomUsuari) {
        return model.listWatchedList(nomClient, nomUsuari);
    }

    public List<String> listContinueWatching(String idClient, String nomUsuari) {
        return model.listContinueWatching(idClient, nomUsuari);
    }

    public void marcarSerieMyList(String idClient, String nomUsuari, String idSerie) {
        model.marcarSerieMyList(idClient, nomUsuari, idSerie);

        // Data persistence layer dealing
    }

    public void valorarEpisodi(int id, String idClient, String nomUsuari, String idSerie, String nomEpisodi, int estrelles,
                               String thumb, String data, int numTemporada) {
        model.valorarEpisodi(id, idClient, nomUsuari, idSerie, nomEpisodi, estrelles, thumb, data, numTemporada);
    }

    //metode necessari a formEpisodi, s'han afegit els altres corresponentment
    public float getDuracioEpisodi(String idSerie, int numTemporada, String idEpisodi) {
        return model.getDuracioEpisodi(idSerie, numTemporada, idEpisodi);
    }

    //metode neccesari a formEpisodi
    public boolean isEpisodiValorat(String idSerie,int numTemporada, String nomEpisodi, String idClient, String idUsuari){
        return model.isEpisodiValorat(idSerie,numTemporada,nomEpisodi,idClient,idUsuari);
    }

    //metode necessari a formUBFlix
    public List<String> getTemporades(String idSerie){
        return model.getTemporades(idSerie);
    }

    public List<Episodi> getEpisodis(String idSerie, String temporada){
        return model.getEpisodis(idSerie,temporada);
    }

    public String getDescripcioEpisodi(String idSerie,int numTemporada, String idEpisodi){
        return model.getDescripcioEpisodi(idSerie,numTemporada,idEpisodi);
    }

    public void visualitzarEpisodi(int id, String idClient, String nomUsuari, String idSerie, String nomEpisodi,
                                   String estat, String data, int minutsRestants, int numTemp) {
        model.visualitzarEpisodi(id, idClient, nomUsuari, idSerie, nomEpisodi, estat, data, minutsRestants, numTemp);
    }

    public List<String> getTopTenVal(String loggedInClient, String loggedInUser) {
            return model.getTopTenVal(loggedInClient, loggedInUser);
    }

    public List<String> getTopTenVis(String loggedInClient, String loggedInUser) {
            return model.getTopTenVis(loggedInClient, loggedInUser);
    }

    public int getDuracioVisualitzada(String idClient, String idUsuari, Episodi episodi) {
        return model.getDuracioVisualitzada(idClient, idUsuari, episodi);
    }
}
