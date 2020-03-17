package edu.ub.ubflix.resources.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactorySQL {

    // Credencials de la Base de Dades pels grups A i C
    /* private static final String URL = "jdbc:db2://dashdb-txn-sbox-yp-lon02-01.services.eu-gb.bluemix.net:50000/BLUDB";
    private static final String USER = "mds79569"; // TODO CREAR USER NO ADMIN A LA BD
    private static final String PWD = "d3sfqkms@4nskslm";*/

    // Credencials de la Base de Dades per grups B i F
    private static final String URL = "jdbc:db2://dashdb-txn-sbox-yp-lon02-02.services.eu-gb.bluemix.net:50000/BLUDB";
    private static final String USER = "xnh76745"; // TODO CREAR USER NO ADMIN A LA BD
    private static final String PWD = "9wkx9x23+fjfx514";

    public static Connection getConnection() {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException | ClassNotFoundException sqlException){
            sqlException.printStackTrace();
            throw new RuntimeException("Error al connectar amb la base de dades", sqlException);
        }
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (!connection.isClosed())
            connection.close();
    }


}
