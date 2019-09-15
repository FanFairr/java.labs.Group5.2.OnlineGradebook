package DAOImpl;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for connection to database.
 * @author Andrey Sherstyuk
 */
class DAOConnection {

    private static final Logger DAOCONNECTIONLOGGER = Logger.getLogger(DAOConnection.class);

    private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:Druce";
    private final static String LOGIN = "system";
    private final static String PASSWORD = "191195";

    static Connection connection;
    private static boolean isConnected;

    /**
     * Method for connection to database.
     */
    static void connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            isConnected = connection == null;
        } catch (ClassNotFoundException | SQLException e) {
            DAOCONNECTIONLOGGER.error("Error connection to database. Message: " + e.getMessage());
        }
    }

    /**
     * Method for disconnect from database.
     */
    static void disconnect() {
        try {
            connection.close();
            isConnected = connection == null;
        } catch (SQLException e) {
            DAOCONNECTIONLOGGER.error("Error disconnect from database. Message: " + e.getMessage());
        }
    }

    /**
     * Method for checking connection to database.
     * @return
     */
    public static boolean isIsConnected() {
        return isConnected;
    }
}
