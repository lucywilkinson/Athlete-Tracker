package models;

/**
 * Created by mattu on 11/13/16.
 */

import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Model {

    public Model() throws SQLException, IOException, ClassNotFoundException {
        dbConnect();
    }

    protected static Connection conn;

    /**
     * Gets properties needed to establish connection to DB.
     * @return properties from config.properties.
     * @throws IOException
     */
    private Properties getProperties() throws IOException {
        Properties properties = new Properties();

        InputStream in = getClass().getResourceAsStream("config.properties");
        properties.load(in);
        in.close();

        return properties;
    }

    /**
     * Establish connection to mysql database. Requires config.properties with db connection fields.
     * @return Connection to mysql database
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public void dbConnect() throws SQLException, IOException, ClassNotFoundException {

        Properties dbProperties = getProperties();

        String dbURL    = dbProperties.getProperty("dbURL");
        String dbUser   = dbProperties.getProperty("dbUser");
        String dbPass   = dbProperties.getProperty("dbPass");
        String dbDriver = dbProperties.getProperty("dbDriver");

        Class.forName(dbDriver);
        this.conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
    }

    /**
     * Closes connection to DB
     * @throws SQLException
     */
    public void dbDisconnect() throws SQLException {
        conn.close();
    }
}
