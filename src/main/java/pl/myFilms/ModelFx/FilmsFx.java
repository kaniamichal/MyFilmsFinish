package pl.myFilms.ModelFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class FilmsFx {

    private IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<CategoryFx> categoryFxFilmsFx = new SimpleObjectProperty<>();
    private ObjectProperty<DirectorFx> directorFxFilmsFx = new SimpleObjectProperty<>();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private ObjectProperty<LocalDate> year = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public CategoryFx getCategoryFxFilmsFx() {
        return categoryFxFilmsFx.get();
    }

    public ObjectProperty<CategoryFx> categoryFxFilmsFxProperty() {
        return categoryFxFilmsFx;
    }

    public void setCategoryFxFilmsFx(CategoryFx categoryFxFilmsFx) {
        this.categoryFxFilmsFx.set(categoryFxFilmsFx);
    }

    public DirectorFx getDirectorFxFilmsFx() {
        return directorFxFilmsFx.get();
    }

    public ObjectProperty<DirectorFx> directorFxFilmsFxProperty() {
        return directorFxFilmsFx;
    }

    public void setDirectorFxFilmsFx(DirectorFx directorFxFilmsFx) {
        this.directorFxFilmsFx.set(directorFxFilmsFx);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public LocalDate getYear() {
        return year.get();
    }

    public ObjectProperty<LocalDate> yearProperty() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year.set(year);
    }

    @Override
    public String toString() {
        return "FilmsFx{" +
                "id=" + id.get() +
                ", categoryFx=" + categoryFxFilmsFx.get() +
                ", authorFx=" + directorFxFilmsFx.get() +
                ", title=" + title.get() +
                ", description=" + description.get() +
                ", releaseDate=" + year.get() +
                '}';
    }
}
