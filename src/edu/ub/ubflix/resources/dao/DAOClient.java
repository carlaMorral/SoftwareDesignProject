package edu.ub.ubflix.resources.dao;

import ub.edu.resources.dao.DAO;
import edu.ub.ubflix.model.Client;

public interface DAOClient extends DAO<Client> {
    Client  findClientByUserNAmeAndPassword(String usuari, String pwd) throws Exception;
}
