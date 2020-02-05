package pl.myFilms.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import pl.myFilms.Utilities.DialogsUtilities;
import pl.myFilms.Utilities.Exception.ApplicationException;
import pl.myFilms.database.models.Film;

import java.sql.SQLException;

public class FilmDao extends CommonDao {
    public FilmDao() {
        super();
    }

    public void deleteByColumnName(String columnName, int id) {

        try {
            Dao<Film, Object> dao = getDao(Film.class);
            DeleteBuilder<Film, Object> deleteBuilder = dao.deleteBuilder();
            try {
                deleteBuilder.where().eq(columnName, id);
                dao.delete(deleteBuilder.prepare());
            } catch (SQLException e) {
                DialogsUtilities.errorDialog(e.getMessage());
            }
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }

    }
}
