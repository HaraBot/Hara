package nz.co.jammehcow.Handlers;

import nz.co.jammehcow.Main;
import org.hsqldb.Server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author jammehcow
 */

public class DBHandler {
    private static final Server server = new Server();

    public static synchronized void init() {
        server.setAddress("127.0.0.1");
        server.setPort(3306);
        server.setDatabasePath(0, "file:" + Main.getJarFolder() + "/haradb");
        server.setDatabaseName(0, "hara_internals");
        server.setNoSystemExit(true);
        server.start();

        try {
            Class.forName("org.hsqldb.jdbcDriver" );
        } catch (Exception e) {
            System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return;
        }

        try {
            Connection con = DriverManager.getConnection("127.0.0.1", "hara", "harabotinternals");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static boolean isValid() throws IOException {
        if (server.isNotRunning()) throw new IOException("The specified database isn't running.");

        // TODO: check against internal DB or snapshot from resources.

        return true;
    }

    public static void shutdown() { server.shutdown(); }
}
