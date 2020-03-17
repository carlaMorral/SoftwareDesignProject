package edu.ub.ubflix.resources.service;

import jdk.nashorn.internal.runtime.ECMAException;
import edu.ub.ubflix.model.*;
import edu.ub.ubflix.resources.dao.*;

import java.sql.SQLException;
import java.util.*;

public class DataService  {
    private DAOSerie serieDAO;
    private DAOTemporada temporadaDAO;
    private DAOEpisodi episodiDAO;
    private DAOArtista artistaDAO;
    private DAOProductora productoraDAO;
    private DAOClient clientDAO;
    private DAOUsuari usuariDAO;
    private DAOVisualitzacio visualitzacioDAO;
    private DAOValoracio valoracioDAO;
    private DAOFavorit favoritDAO;

    public DataService(AbstractFactoryData factory) {

        this.serieDAO = factory.createDAOSerie();
        this.temporadaDAO = factory.createDAOTemporada();
        this.episodiDAO = factory.createDAOEpisodi();
        this.productoraDAO = factory.createDAOProductora();
        this.artistaDAO = factory.createDAOArtista();
        this.clientDAO = factory.createDAOClient();
        this.usuariDAO = factory.createDAOUsuari();
        this.visualitzacioDAO = factory.createDAOVisualitzacio();
        this.valoracioDAO = factory.createDAOValoracio();
        this.favoritDAO = factory.createDAOFavorit();
    }

    public List<Serie> getAllSeries() throws Exception {
        return serieDAO.getAll();
    }

    public List<Temporada> getAllTemporades() throws Exception {
        return temporadaDAO.getAll();
    }

    public List<Episodi> getAllEpisodis() throws Exception {
        return episodiDAO.getAll();
    }

    public List<Productora> getAllProductores() throws Exception {
        return productoraDAO.getAll();
    }

    public List<Artista> getAllArtistes() throws Exception {
        return artistaDAO.getAll();
    }

    public List<Client> getAllClients() throws Exception {
        return clientDAO.getAll();
    }

    public List<Usuari> getAllUsuaris() throws Exception {
        return usuariDAO.getAll();
    }

    public List<Visualitzacio> getAllVisualitzacions() throws Exception {
        return visualitzacioDAO.getAll();
    }

    public List<Valoracio> getAllValoracions() throws Exception {
        return valoracioDAO.getAll();
    }

    public List<Favorit> getAllFavorits() throws Exception {
        return favoritDAO.getAll();
    }

    public Client getClientByUsuariAndPassword(String usuari, String password) {
        Client client = null;
        try {
            client = clientDAO.findClientByUserNAmeAndPassword(usuari, password);
        } catch (Exception e) {
            //TODO
        }
        return client;
    }

    public List<Usuari> getUsuariByClient(String idClient) throws Exception {
        List<Usuari> usuariList = getAllUsuaris();
        List<Usuari> usuariByClientList = new ArrayList<>();
        for (Usuari usuari : usuariList) {
            if (usuari.getIdClient().equalsIgnoreCase(idClient))
                usuariByClientList.add(usuari);
        }
        return usuariByClientList;
    }

    public boolean addClient(Client client) throws Exception {
        return clientDAO.add(client);
    }

    public boolean addUsuari(Usuari usuari) throws Exception {
        return usuariDAO.add(usuari);
    }

    public List<Episodi> findAllBySerieAndTemporada(String idSerie, int numTemporada) throws Exception {
        return episodiDAO.findAllBySerieAndTemporada(idSerie, numTemporada);
    }

}
