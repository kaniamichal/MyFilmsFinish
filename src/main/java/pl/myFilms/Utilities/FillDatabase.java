package pl.myFilms.Utilities;

import pl.myFilms.Utilities.Exception.ApplicationException;
import pl.myFilms.database.dao.CategoryDao;
import pl.myFilms.database.dao.FilmDao;
import pl.myFilms.database.dbutils.DbManager;
import pl.myFilms.database.models.Category;
import pl.myFilms.database.models.Director;
import pl.myFilms.database.models.Film;

import java.util.Date;

/**
 * Created by ZacznijProgramowac.
 * https://www.youtube.com/zacznijprogramowac
 * http://zacznijprogramowac.net/
 * Służy jako wypełniacz bazy danych
 */
public class FillDatabase {
    public static  void fillDatabase(){
        Category category1 = new Category();
        category1.setName("Dramat");
        Director dicetor1 = new Director();
        dicetor1.setName("William");
        dicetor1.setSurname("Szekspir");
        Film film1 = new Film();
        film1.setCategory(category1);
        film1.setDirector(dicetor1);
        film1.setTitle("Makbet");
       film1.setReleaseDate(new Date());
       film1.setDescription("Byłaby to fajna książka, gdyby nie była lekturą");


        Category category2 = new Category();
        category2.setName("Sensacja");
        CategoryDao categoryDao = new CategoryDao();
        try {
            categoryDao.creatOrUpdate(category2);
            DbManager.closeConnectionSource();
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }


        Category category3 = new Category();
        category3.setName("Reportaż");
        Director director2 = new Director();
        director2.setName("Mariusz");
        director2.setSurname("Szczygieł");
        Film film2 = new Film();
        film2.setCategory(category3);
        film2.setDirector(director2);
        film2.setTitle("Gottland");
        film2.setReleaseDate(new Date());
        film2.setDescription("Ciekawe reportaże, ze świata");

        Category category4 = new Category();
        category4.setName("Fantastyka");
        Director director3 = new Director();
        director3.setName("John Ronald Reuel");
        director3.setSurname("Tolkien");
        Film film3 = new Film();
        film3.setCategory(category4);
        film3.setDirector(director3);
        film3.setTitle("Władca Pierścieni");
        film3.setReleaseDate(new Date());
        film3.setDescription("O dwóch takich, co nieśli pierścień");

        Director director4 = new Director();
        director4.setName("Terry ");
        director4.setSurname("Pratchett");
        Film film4 = new Film();
        film4.setCategory(category4);
        film4.setDirector(director4);
        film4.setTitle("Kolor magii");
        film4.setReleaseDate(new Date());
        film4.setDescription("Do przeczytania");

        FilmDao filmDao = new FilmDao();
        try {
            filmDao.creatOrUpdate(film1);
            filmDao.creatOrUpdate(film2);
            filmDao.creatOrUpdate(film3);
            filmDao.creatOrUpdate(film4);
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
            e.printStackTrace();
        }
        DbManager.closeConnectionSource();
    }
}
