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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListFilmsModel {

    private ObservableList<FilmsFx> filmsFxObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList();
    private ObservableList<DirectorFx> directorFxObservableList = FXCollections.observableArrayList();

    private ObjectProperty<DirectorFx> directorFxObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();

    private List<FilmsFx> filmsFxList = new ArrayList<>();

    public void init() {
        try {
            FilmDao filmDao = new FilmDao();
            List<Film> films = filmDao.queryForAll(Film.class);
            filmsFxList.clear();
            films.forEach(film -> {
                this.filmsFxList.add(ConverterFilm.converterToFilmFx(film));
            });
            this.filmsFxObservableList.setAll(filmsFxList);
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
        initDirectors();
        initCategory();

    }

    public void filterFilmsList() {
        if (getDirectorFxObjectProperty() != null && getCategoryFxObjectProperty() != null) {
            filterPredicate(predicateDirector().and(predicateCategory()));
        } else if (getDirectorFxObjectProperty() != null) {
            filterPredicate(predicateDirector());
        } else if (getCategoryFxObjectProperty() != null) {
            filterPredicate(predicateCategory());
        } else {
            this.filmsFxObservableList.setAll(filmsFxList);
        }
    }

    private void initDirectors() {
        try {
            DirectorDao directorDao = new DirectorDao();
            List<Director> directorList = directorDao.queryForAll(Director.class);
            this.directorFxObservableList.clear();
            directorList.forEach(director -> {
                DirectorFx directorFx = ConverterDirector.convertToDirectorFx(director);
                this.directorFxObservableList.add(directorFx);
            });
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }

    private void initCategory() {
        try {
            CategoryDao categoryDao = new CategoryDao();
            List<Category> categoryList = categoryDao.queryForAll(Category.class);
            this.categoryFxObservableList.clear();
            categoryList.forEach(category -> {
                CategoryFx categoryFx = ConverterCategory.convertToCategoryFx(category);
                this.categoryFxObservableList.add(categoryFx);
            });
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }

    }

    private Predicate<FilmsFx> predicateCategory() {
        return filmsFx -> filmsFx.getCategoryFxFilmsFx().getId() == getCategoryFxObjectProperty().getId();
    }

    private Predicate<FilmsFx> predicateDirector() {
        return filmsFx -> filmsFx.getDirectorFxFilmsFx().getId() == getDirectorFxObjectProperty().getId();
    }

    private void filterPredicate(Predicate<FilmsFx> predicate) {
        List<FilmsFx> newList = filmsFxList.stream().filter(predicate).collect(Collectors.toList());
        this.filmsFxObservableList.setAll(newList);
    }


    public void deleteFilms(FilmsFx filmsFx) {
        try {
            FilmDao filmDao = new FilmDao();
            filmDao.deleteById(Film.class, filmsFx.getId());
            init();
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }

    public ObservableList<FilmsFx> getFilmsFxObservableList() {
        return filmsFxObservableList;
    }

    public void setFilmsFxObservableList(ObservableList<FilmsFx> filmsFxObservableList) {
        this.filmsFxObservableList = filmsFxObservableList;
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

    public DirectorFx getDirectorFxObjectProperty() {
        return directorFxObjectProperty.get();
    }

    public ObjectProperty<DirectorFx> directorFxObjectPropertyProperty() {
        return directorFxObjectProperty;
    }

    public void setDirectorFxObjectProperty(DirectorFx directorFxObjectProperty) {
        this.directorFxObjectProperty.set(directorFxObjectProperty);
    }

    public CategoryFx getCategoryFxObjectProperty() {
        return categoryFxObjectProperty.get();
    }

    public ObjectProperty<CategoryFx> categoryFxObjectPropertyProperty() {
        return categoryFxObjectProperty;
    }

    public void setCategoryFxObjectProperty(CategoryFx categoryFxObjectProperty) {
        this.categoryFxObjectProperty.set(categoryFxObjectProperty);
    }
}
