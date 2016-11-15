package main.models;

/**
 * Created by mattu on 11/13/16.
 */

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import java.sql.*;

public class Model {
    protected static Connection conn;

    public Model() throws ClassNotFoundException, SQLException {
        dbConnect();
    }

    /* Establish Connection to Database */
    public void dbConnect() throws ClassNotFoundException, SQLException {

        /* TODO: Move DB credentials to properties file */
        String dbURL    = "jdbc:mysql://localhost:3306/athlete_tracker?autoReconnect=true&useSSL=false";
        String dbUser   = "root";
        String dbPass   = "Isildur=87";
        String dbDriver = "com.mysql.jdbc.Driver";

        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
    }

    public void dbDisconnect() throws SQLException {
        conn.close();
    }
}
