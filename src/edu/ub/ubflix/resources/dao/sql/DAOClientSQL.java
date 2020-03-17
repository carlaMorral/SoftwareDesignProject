package edu.ub.ubflix.resources.dao.sql;

import edu.ub.ubflix.resources.dao.DAOClient;
import edu.ub.ubflix.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOClientSQL implements DAOClient {

    /**
     * @param id unique identifier of the customer.
     * @return an optional with customer if a customer with unique identifier <code>id</code>
     *     exists, empty optional otherwise.
     * @throws Exception if any error occurs.
     */
    @Override
    public Optional<Client> getById(String id) throws Exception {
        Connection connection = ConnectionFactorySQL.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM v_client Where id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return Optional.of(extractClientFromResultSet(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return Optional.empty();
    }

    /**
     * @return all the customers as a List.
     * @throws Exception if any error occurs.
     */
    @Override
    public List<Client> getAll() throws Exception {
        List<Client> clientList = new ArrayList<>();
        Connection connection = ConnectionFactorySQL.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM v_client");
            while(rs.next()) {
                Client client = extractClientFromResultSet(rs);
                clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return clientList;
    }
    /**
     * @param client the customer to be added.
     * @return true if customer is successfully added, false if customer already exists.
     * @throws Exception if any error occurs.
     */
    @Override
    public boolean add(Client client) throws Exception {
        Connection connection = ConnectionFactorySQL.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO client VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, client.getIdClient());
            ps.setString(2, client.getPwd());
            ps.setString(3, client.getName());
            ps.setString(4, client.getDni());
            ps.setString(5, client.getAddress());
            if (ps.executeUpdate() == 1) return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return false;
    }

    /**
     * @param client the customer to be updated.
     * @return true if customer exists and is successfully updated, false otherwise.
     * @throws Exception if any error occurs.
     */
    @Override
    public boolean update(Client client) {
        return false;
    }

    /**
     * @param client the customer to be deleted.
     * @return true if customer exists and is successfully deleted, false otherwise.
     * @throws Exception if any error occurs.
     */
    @Override
    public boolean delete(Client client) throws Exception  {
        return false;
    }

    @Override
    public Client findClientByUserNAmeAndPassword(String idclient, String pwd) throws Exception {
        Connection connection = ConnectionFactorySQL.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM v_client Where id=? AND pwd=?");
            ps.setString(1, idclient);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return extractClientFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            ConnectionFactorySQL.closeConnection(connection);
        }
        return null;
    }

    private Client extractClientFromResultSet(ResultSet rs) throws SQLException {
        Client client = new Client(
                rs.getString("id"),
                rs.getString("pwd"),
                rs.getString("nom"),
                rs.getString("dni"),
                rs.getString("adreca"),
                false
        );
        return client;
    }
}
