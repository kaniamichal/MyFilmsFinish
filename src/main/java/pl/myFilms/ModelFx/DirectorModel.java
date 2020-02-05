package pl.myFilms.ModelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.myFilms.Utilities.Converters.ConverterDirector;
import pl.myFilms.Utilities.DialogsUtilities;
import pl.myFilms.Utilities.Exception.ApplicationException;
import pl.myFilms.database.dao.DirectorDao;
import pl.myFilms.database.dao.FilmDao;
import pl.myFilms.database.models.Director;
import pl.myFilms.database.models.Film;

import java.util.List;

public class DirectorModel {

    private ObjectProperty<DirectorFx> directorFxObjectProperty = new SimpleObjectProperty<>(new DirectorFx());
    private ObjectProperty<DirectorFx> directorFxObjectPropertyEdit = new SimpleObjectProperty<>(new DirectorFx());

    private ObservableList<DirectorFx> directorFxObservableList = FXCollections.observableArrayList();

    public void init() {
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

    public void saveAuthorEditInDatabase() {
        SaveOrUpdate(this.getDirectorFxObjectPropertyEdit());
    }

    public void saveAuthorInDatabase() {
        SaveOrUpdate(this.getDirectorFxObjectProperty());
    }

    private void SaveOrUpdate(DirectorFx directorFxObjectPropertyEdit) {
        try {
            DirectorDao directorDao = new DirectorDao();
            Director director = ConverterDirector.convertToDirector(directorFxObjectPropertyEdit);
            directorDao.creatOrUpdate(director);
            this.init();
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }

    public void deleteDirectorInDataBaseById() {
        try {
            DirectorDao directorDao = new DirectorDao();
            directorDao.deleteById(Director.class, this.getDirectorFxObjectPropertyEdit().getId());
            FilmDao filmDao = new FilmDao();
            filmDao.deleteByColumnName(Film.DIRECTOR_ID, this.getDirectorFxObjectPropertyEdit().getId());
            this.init();
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
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

    public ObservableList<DirectorFx> getDirectorFxObservableList() {
        return directorFxObservableList;
    }

    public void setDirectorFxObservableList(ObservableList<DirectorFx> directorFxObservableList) {
        this.directorFxObservableList = directorFxObservableList;
    }

    public DirectorFx getDirectorFxObjectPropertyEdit() {
        return directorFxObjectPropertyEdit.get();
    }

    public ObjectProperty<DirectorFx> directorFxObjectPropertyEditProperty() {
        return directorFxObjectPropertyEdit;
    }

    public void setDirectorFxObjectPropertyEdit(DirectorFx directorFxObjectPropertyEdit) {
        this.directorFxObjectPropertyEdit.set(directorFxObjectPropertyEdit);
    }
}
