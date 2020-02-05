package pl.myFilms.database.dbutils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.myFilms.database.models.Category;
import pl.myFilms.database.models.Director;
import pl.myFilms.database.models.Film;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:h2:./nowaBazaDanychDB";
    private static final String USER = "admin";
    private static final String PASS = "pass";

    private static ConnectionSource connectionSource;

    public static void initDatabase() {
        getConnectionSource();
        dropTable(); // Delete table
        createTable();
        //   createTable2();
        //   createTable3();
        closeConnectionSource();
    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
            // getConnection(JDBC_DRIVER_HD, USER, PASS);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource() {
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
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTable() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
            TableUtils.createTableIfNotExists(connectionSource, Director.class);
            TableUtils.createTableIfNotExists(connectionSource, Film.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
/*
    public static void createTable2() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Director.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void createTable3() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Film.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
        }
    }*/

    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, Category.class, true);
            TableUtils.dropTable(connectionSource, Director.class, true);
            TableUtils.dropTable(connectionSource, Film.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
