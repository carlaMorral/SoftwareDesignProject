package edu.ub.ubflix.resources.dao.sql;

import edu.ub.ubflix.resources.dao.DAOValoracio;
import edu.ub.ubflix.model.Valoracio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOValoracioSQL implements DAOValoracio {

    @Override
    public Optional<Valoracio> getById(String id) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<Valoracio> getAll() throws Exception {
        List<Valoracio> valoracioList = new ArrayList<>();
        Connection connection = ConnectionFactorySQL.getConnection();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM v_valoracio");

            while (rs.next()) {
                Valoracio valoracio = extractValoracioFromResultSet(rs);
                valoracioList.add(valoracio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return valoracioList;
    }

    @Override
    public boolean add(Valoracio valoracio) throws Exception {
        return false;
    }

    @Override
    public boolean update(Valoracio valoracio) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Valoracio valoracio) throws Exception {
        return false;
    }

    private Valoracio extractValoracioFromResultSet(ResultSet rs) throws SQLException {
        return new Valoracio(
                rs.getInt("id"),
                rs.getString("idclient"),
                rs.getInt("idusuari"),
                rs.getString("idserie"),
                rs.getInt("idepisodi"),
                rs.getInt("estrelles"),
                rs.getString("thumb"),
                rs.getString("data")
        );
    }
}
