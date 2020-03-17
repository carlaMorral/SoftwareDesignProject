package edu.ub.ubflix.resources.dao;

import ub.edu.resources.dao.DAO;
import edu.ub.ubflix.model.Episodi;

import java.util.List;

public interface DAOEpisodi extends DAO<Episodi> {
    // Metodes específics de series si es el cas
    List<Episodi> findAllBySerieAndTemporada(String idSerie, int numTemporada) throws Exception;
}
