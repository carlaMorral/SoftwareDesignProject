package edu.ub.ubflix.resources.dao.sql;

import edu.ub.ubflix.resources.dao.DAOVisualitzacio;
import edu.ub.ubflix.model.Visualitzacio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOVisualitzacioSQL implements DAOVisualitzacio {
    @Override
    public Optional<Visualitzacio> getById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Visualitzacio> getAll() throws Exception {
        List<Visualitzacio> visualitzacioList = new ArrayList<>();
        Connection connection = ConnectionFactorySQL.getConnection();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM v_visualitzacio");

            while (rs.next()) {
                Visualitzacio visualitzacio = extractVisualitzacioFromResultSet(rs);
                visualitzacioList.add(visualitzacio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return visualitzacioList;
    }

    @Override
    public boolean add(Visualitzacio visualitzacio) {
        return false;
    }

    @Override
    public boolean update(Visualitzacio visualitzacio) {
        return false;
    }

    @Override
    public boolean delete(Visualitzacio visualitzacio) {
        return false;
    }

    private Visualitzacio extractVisualitzacioFromResultSet(ResultSet rs) throws SQLException {
        return new Visualitzacio(
                rs.getInt("id"),
                Integer.parseInt(rs.getString("idusuari")),
                rs.getString("idserie"),
                rs.getInt("numtemporada"),
                rs.getInt("idEpisodi"),
                rs.getString("data"),
                rs.getInt("minutsrestants")*60
        );
    }
}
