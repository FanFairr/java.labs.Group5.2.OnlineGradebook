package DAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DAOConnection {

    private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:Druce";
    private final static String LOGIN = "system";
    private final static String PASSWORD = "191195";

    static Connection connection;
    private static boolean isConnected;

    static void connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            isConnected = connection == null;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static void disconnect() {
        try {
            connection.close();
            isConnected = connection == null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isIsConnected() {
        return isConnected;
    }
}
