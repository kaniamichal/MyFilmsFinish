package pl.myFilms.database.dbutils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.myFilms.database.models.Category;
import pl.myFilms.database.models.Director;
import pl.myFilms.database.models.Film;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbManager {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DbManager.class));

    private static final String JDBC_DRIVER_HD = "jdbc:h2:./filmsDB";

    private static final String USER = "admin";
    private static final String PASS = "pass";

    private static ConnectionSource connectionSource;

    public static void initDatabase() {
        createConnectionSource();
      //  dropTable(); // Delete table
        createTable();
        closeConnectionSource();
    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
              //      DriverManager.getConnection(JDBC_DRIVER_HD, USER, PASS);

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            // e.printStackTrace();
        }
    }

    public static com.j256.ormlite.support.ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();

            } catch (IOException e) {
                LOGGER.warning(e.getMessage());
                // e.printStackTrace();
            }
        }
    }

    private static void createTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
            TableUtils.createTableIfNotExists(connectionSource, Director.class);
            TableUtils.createTableIfNotExists(connectionSource, Film.class);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            //e.printStackTrace();
        }
    }

    private static void dropTable(){
        try {
            TableUtils.dropTable(connectionSource, Category.class, true);
            TableUtils.dropTable(connectionSource, Director.class, true);
            TableUtils.dropTable(connectionSource, Film.class, true);
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
//            e.printStackTrace();
        }

    }

}
