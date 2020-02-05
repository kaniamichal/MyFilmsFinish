package pl.myFilms.ModelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.myFilms.Utilities.Converters.ConverterCategory;
import pl.myFilms.Utilities.Converters.ConverterDirector;
import pl.myFilms.Utilities.Converters.ConverterFilm;
import pl.myFilms.Utilities.DialogsUtilities;
import pl.myFilms.Utilities.Exception.ApplicationException;
import pl.myFilms.database.dao.CategoryDao;
import pl.myFilms.database.dao.DirectorDao;
import pl.myFilms.database.dao.FilmDao;
import pl.myFilms.database.models.Category;
import pl.myFilms.database.models.Director;
import pl.myFilms.database.models.Film;

import java.util.List;

public class FilmsModel {

    private ObjectProperty<FilmsFx> filmsFxObjectProperty = new SimpleObjectProperty<>(new FilmsFx());
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
    private ObservableList<DirectorFx> directorFxObservableList = FXCollections.observableArrayList();

    public void init(){
        initDirectorList();
        initCategoryList();
    }

    private void initCategoryList() {

        try {
            CategoryDao categoryDao =new CategoryDao();
            List<Category> categoryList = categoryDao.queryForAll(Category.class);
            categoryFxObservableList.clear();
            categoryList.forEach(category -> {
                CategoryFx categoryFx = ConverterCategory.convertToCategoryFx(category);
                categoryFxObservableList.add(categoryFx);
            } );
        }
        catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }

    private void initDirectorList() {
        try{
            DirectorDao directorDao = new DirectorDao();
            List<Director> directorList = directorDao.queryForAll(Director.class);
            this.directorFxObservableList.clear();
            directorList.forEach(director -> {
                DirectorFx directorFx = ConverterDirector.convertToDirectorFx(director);
                this.directorFxObservableList.add(directorFx);
            });
        }
        catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }

    public void saveFilmInDataBase(){
        Film film = ConverterFilm.convertToFilm(this.getFilmsFxObjectProperty());


        try {
            CategoryDao categoryDao = new CategoryDao();
            Category category = categoryDao.findById(Category.class, this.getFilmsFxObjectProperty().getCategoryFxFilmsFx().getId());

            DirectorDao directorDao = new DirectorDao();
            Director director = directorDao.findById(Director.class, this.getFilmsFxObjectProperty().getDirectorFxFilmsFx().getId());

            film.setCategory(category);
            film.setDirector(director);

            FilmDao filmDao = new FilmDao();
            filmDao.creatOrUpdate(film);

        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }
    public FilmsFx getFilmsFxObjectProperty() {
        return filmsFxObjectProperty.get();
    }

    public ObjectProperty<FilmsFx> filmsFxObjectPropertyProperty() {
        return filmsFxObjectProperty;
    }

    public void setFilmsFxObjectProperty(FilmsFx filmsFxObjectProperty) {
        this.filmsFxObjectProperty.set(filmsFxObjectProperty);
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
    }

    public ObservableList<DirectorFx> getDirectorFxObservableList() {
        return directorFxObservableList;
    }

    public void setDirectorFxObservableList(ObservableList<DirectorFx> directorFxObservableList) {
        this.directorFxObservableList = directorFxObservableList;
    }


}
