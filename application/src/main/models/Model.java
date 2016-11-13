package main.models;

/**
 * Created by mattu on 11/13/16.
 */

import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

import java.sql.*;

public class Model {
    protected static Connection conn;

    /* Establish Connection to Database */
    public Connection dbConnect() throws ClassNotFoundException, SQLException {

        /* TODO: Move DB credentials to properties file */
        String dbURL    = "";
        String dbUser   = "";
        String dbPass   = "";
        String dbDriver = "";

        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
        return conn;
    }

    public void dbDisconnect() throws SQLException {
        conn.close();
    }
}
