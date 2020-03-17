package edu.ub.ubflix.resources.dao.sql;

import edu.ub.ubflix.resources.dao.DAOEpisodi;
import edu.ub.ubflix.model.Episodi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static edu.ub.ubflix.resources.dao.sql.ConnectionFactorySQL.closeConnection;

public class DAOEpisodiSQL implements DAOEpisodi {

    public List<Episodi> findAllBySerieAndTemporada(String idSerie, int numTemporada) throws SQLException{
        List<Episodi> episodiList = new ArrayList<>();
        Connection connection = ConnectionFactorySQL.getConnection();
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM v_episodi Where IDSERIE=? AND NUMTEMPORADA=?");
            ps.setString(1, idSerie);
            ps.setInt(2, numTemporada);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Episodi episodi = extractEpisodiFromResultSet(rs);
                episodiList.add(episodi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (!connection.isClosed())
                connection.close();
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return episodiList;
    }

    @Override
    public Optional<Episodi> getById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Episodi> getAll() throws Exception {
        List<Episodi> episodiList = new ArrayList<>();
        Connection connection = ConnectionFactorySQL.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM v_episodi");

            while (rs.next()) {
                Episodi episodi = extractEpisodiFromResultSet(rs);
                episodiList.add(episodi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (!connection.isClosed())
                connection.close();
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return episodiList;
    }

    @Override
    public boolean add(Episodi episodi) throws Exception {
        return false;
    }

    @Override
    public boolean update(Episodi episodi) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Episodi episodi) throws Exception {
        return false;
    }

    private Episodi extractEpisodiFromResultSet(ResultSet rs) throws SQLException {
        return new Episodi(
                rs.getString("idserie"),
                rs.getInt("numtemporada"),
                rs.getInt("idepisodi"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("idioma"),
                rs.getString("data"),
                rs.getString("duracio")
        );

    }

}
