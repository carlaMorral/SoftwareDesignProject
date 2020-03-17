package edu.ub.ubflix.resources.service;

import edu.ub.ubflix.resources.dao.*;
import edu.ub.ubflix.resources.dao.sql.*;

public class FactorySQL implements AbstractFactoryData {

    @Override
    public DAOClient createDAOClient() {
        return new DAOClientSQL();
    }

    @Override
    public DAOSerie createDAOSerie() {
        return new DAOSerieSQL(); // sa falta d'implementar new DAOSerieSQL();
    }

    @Override
    public DAOTemporada createDAOTemporada() {
        return new DAOTemporadaSQL();
    }

    @Override
    public DAOEpisodi createDAOEpisodi() {
        return new DAOEpisodiSQL();
    }

    @Override
    public DAOProductora createDAOProductora() {
        return new DAOProductoraSQL();
    };

    @Override
    public DAOArtista createDAOArtista() {
        return new DAOArtistaSQL();
    }

    @Override
    public DAOUsuariSQL createDAOUsuari() {
        return new DAOUsuariSQL();
    }

    @Override
    public DAOVisualitzacio createDAOVisualitzacio() {
        return new DAOVisualitzacioSQL();
    }

    @Override
    public DAOValoracio createDAOValoracio() {
        return new DAOValoracioSQL();
    }

    @Override
    public DAOFavorit createDAOFavorit() {
        return new DAOFavoritSQL();
    }
}
