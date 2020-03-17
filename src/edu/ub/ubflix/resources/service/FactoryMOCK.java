package edu.ub.ubflix.resources.service;

import edu.ub.ubflix.resources.dao.*;


public class FactoryMOCK implements AbstractFactoryData {

    @Override
    public DAOClient createDAOClient() {
        return null;
    }

    @Override
    public DAOUsuari createDAOUsuari() {
        return null;
    }

    @Override
    public DAOVisualitzacio createDAOVisualitzacio() {
        return null;
    }

    @Override
    public DAOValoracio createDAOValoracio() {
        return null;
    }

    @Override
    public DAOFavorit createDAOFavorit() {
        return null;
    }

    @Override
    public DAOSerie createDAOSerie() {
        return null;
    }

    @Override
    public DAOTemporada createDAOTemporada() {
        return null;
    }

    @Override
    public DAOEpisodi createDAOEpisodi() {
        return null;
    }

    @Override
    public DAOArtista createDAOArtista() {
        return null;
    }

    @Override
    public DAOProductora createDAOProductora() {
        return null;
    };

}
