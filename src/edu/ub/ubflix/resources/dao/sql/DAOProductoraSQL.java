package edu.ub.ubflix.resources.dao.sql;

import edu.ub.ubflix.resources.dao.DAOProductora;
import edu.ub.ubflix.model.Productora;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOProductoraSQL implements DAOProductora {

    @Override
    public Optional<Productora> getById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Productora> getAll() throws SQLException {
        List<Productora> productoraList = new ArrayList<>();
        Connection connection = ConnectionFactorySQL.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM v_productora");

            while (rs.next()) {
                Productora productora = extractProductoraFromResultSet(rs);
                productoraList.add(productora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return productoraList;
    }

    @Override
    public boolean add(Productora productora) {
        return false;
    }

    @Override
    public boolean update(Productora productora) {
        return false;
    }

    @Override
    public boolean delete(Productora productora) {
        return false;
    }

    private Productora extractProductoraFromResultSet(ResultSet rs) throws SQLException {
        Productora productora = new Productora(rs.getString("nom"), rs.getInt("id"));
        return productora;
    }
}
