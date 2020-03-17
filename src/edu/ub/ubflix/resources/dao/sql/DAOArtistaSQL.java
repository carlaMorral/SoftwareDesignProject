package edu.ub.ubflix.resources.dao.sql;

import edu.ub.ubflix.resources.dao.DAOArtista;
import edu.ub.ubflix.model.Artista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOArtistaSQL implements DAOArtista {

    @Override
    public Optional<Artista> getById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Artista> getAll() throws SQLException {
        List<Artista> productoraList = new ArrayList<>();
        Connection connection = ConnectionFactorySQL.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM v_artista");

            while (rs.next()) {
                Artista artista = extractArtistaFromResultSet(rs);
                productoraList.add(artista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return productoraList;
    }

    @Override
    public boolean add(Artista artista) {
        return false;
    }

    @Override
    public boolean update(Artista artista) {
        return false;
    }

    @Override
    public boolean delete(Artista artista) {
        return false;
    }

    private Artista extractArtistaFromResultSet(ResultSet rs) throws SQLException {
        Artista artista = new Artista(
                rs.getInt("id"),
                rs.getString("idserie"),
                rs.getString("nom"),
                rs.getString("nacionalitat"),
                rs.getString("tipus")
        );
        return artista;
    }
}
