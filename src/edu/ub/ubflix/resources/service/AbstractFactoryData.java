package edu.ub.ubflix.resources.service;

import edu.ub.ubflix.resources.dao.*;

public interface AbstractFactoryData {
    DAOSerie createDAOSerie();
    DAOTemporada createDAOTemporada();
    DAOEpisodi createDAOEpisodi();
    DAOProductora createDAOProductora();
    DAOArtista createDAOArtista();
    DAOClient createDAOClient();
    DAOUsuari createDAOUsuari();
    DAOVisualitzacio createDAOVisualitzacio();
    DAOValoracio createDAOValoracio();
    DAOFavorit createDAOFavorit();
}