package pl.myFilms.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.myFilms.ModelFx.CategoryFx;
import pl.myFilms.ModelFx.DirectorFx;
import pl.myFilms.ModelFx.FilmsModel;

public class FilmsController {

    @FXML
    private TextArea descriptionTextAreaFilms;
    @FXML
    private DatePicker yearFilmsDatePicker;
    @FXML
    private TextField titleFilmsTextField;
    @FXML
    private ComboBox<CategoryFx> comboboxCategoryFilms;
    @FXML
    private ComboBox<DirectorFx> comboboxDirectorFilms;
    @FXML
    private Button addFilmsButton;

    public FilmsModel filmsModel;

    public FilmsController() {
    }

    @FXML
    public void initialize() {
        this.filmsModel = new FilmsModel();
        this.filmsModel.init();
        bindings();
        validation();

    }

    private void validation() {
        this.addFilmsButton.disableProperty().bind(this.comboboxCategoryFilms.valueProperty().isNull().or
                (this.comboboxDirectorFilms.valueProperty().isNull().or(this.titleFilmsTextField.textProperty().isEmpty()).
                        or(this.descriptionTextAreaFilms.textProperty().isEmpty()).or(this.yearFilmsDatePicker.valueProperty().isNull())));
    }

    public void bindings() {
        this.comboboxCategoryFilms.setItems(this.filmsModel.getCategoryFxObservableList());
        this.comboboxDirectorFilms.setItems(this.filmsModel.getDirectorFxObservableList());

        this.comboboxCategoryFilms.valueProperty().bindBidirectional(this.filmsModel.getFilmsFxObjectProperty().categoryFxFilmsFxProperty());
        this.comboboxDirectorFilms.valueProperty().bindBidirectional(this.filmsModel.getFilmsFxObjectProperty().directorFxFilmsFxProperty());
        this.titleFilmsTextField.textProperty().bindBidirectional(this.filmsModel.getFilmsFxObjectProperty().titleProperty());
        this.descriptionTextAreaFilms.textProperty().bindBidirectional(this.filmsModel.getFilmsFxObjectProperty().descriptionProperty());
        this.yearFilmsDatePicker.valueProperty().bindBidirectional(this.filmsModel.getFilmsFxObjectProperty().yearProperty());
    }

    public void addFilmsInDataBase() {
        this.filmsModel.saveFilmInDataBase();
        clearFields();
    }

    private void clearFields() {
        this.comboboxDirectorFilms.getSelectionModel().clearSelection();
        this.comboboxCategoryFilms.getSelectionModel().clearSelection();
        this.titleFilmsTextField.clear();
        this.descriptionTextAreaFilms.clear();
        this.yearFilmsDatePicker.getEditor().clear();
    }

    public FilmsModel getFilmsModel() {
        return filmsModel;
    }
}
