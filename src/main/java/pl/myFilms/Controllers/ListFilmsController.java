package pl.myFilms.Controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.myFilms.ModelFx.CategoryFx;
import pl.myFilms.ModelFx.DirectorFx;
import pl.myFilms.ModelFx.FilmsFx;
import pl.myFilms.ModelFx.ListFilmsModel;
import pl.myFilms.Utilities.DialogsUtilities;
import pl.myFilms.Utilities.FxmlUtilities;

import java.io.IOException;
import java.time.LocalDate;

public class ListFilmsController {

    @FXML
    private Button clearDirectorComboBoxButton;
    @FXML
    private Button clearCategoryComboBoxButton;
    @FXML
    private HBox AllFilmsFxml;
    @FXML
    private TableView<FilmsFx> tableViewFilms;
    @FXML
    private TableColumn<FilmsFx, String> titleColumn;
    @FXML
    private TableColumn<FilmsFx, String> descriptionColumn;
    @FXML
    private TableColumn<FilmsFx, DirectorFx> directorColumn;
    @FXML
    private TableColumn<FilmsFx, CategoryFx> categoryColumn;
    @FXML
    private TableColumn<FilmsFx, LocalDate> yearColumn;
    @FXML
    private TableColumn<FilmsFx, FilmsFx> deleteColumn;
    @FXML
    private TableColumn<FilmsFx, FilmsFx> editColumn;
    @FXML
    private ComboBox allFilmsCategoryComboBox;
    @FXML
    private ComboBox allFilmsDirectorComboBox;


    private ListFilmsModel listFilmsModel;

    public void initialize() {
        this.listFilmsModel = new ListFilmsModel();
        this.listFilmsModel.init();

        this.allFilmsCategoryComboBox.setItems(this.listFilmsModel.getCategoryFxObservableList());
        this.allFilmsDirectorComboBox.setItems(this.listFilmsModel.getDirectorFxObservableList());
        this.listFilmsModel.categoryFxObjectPropertyProperty().bind(this.allFilmsCategoryComboBox.valueProperty());
        this.listFilmsModel.directorFxObjectPropertyProperty().bind(this.allFilmsDirectorComboBox.valueProperty());

        this.tableViewFilms.setItems(this.listFilmsModel.getFilmsFxObservableList());
        this.titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        this.descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        this.yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        this.directorColumn.setCellValueFactory(cellData -> cellData.getValue().directorFxFilmsFxProperty());
        this.categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryFxFilmsFxProperty());
        this.deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        this.editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        this.deleteColumn.setCellFactory(param -> new TableCell<FilmsFx, FilmsFx>() {
            Button button = createButton("/icons/delete.png ");

            @Override
            protected void updateItem(FilmsFx item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(actionEvent -> {
                        try {
                            listFilmsModel.deleteFilms(item);
                        } catch (Exception e) {
                            DialogsUtilities.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });


        this.editColumn.setCellFactory(param -> new TableCell<FilmsFx, FilmsFx>() {
            Button button = createButton("/icons/edit.png");

            @Override
            protected void updateItem(FilmsFx item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = FxmlUtilities.getLoader("/fxml/AddFilms.fxml");
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogsUtilities.errorDialog(e.getMessage());
                        }
                            FilmsController controller = loader.getController();
                            controller.getFilmsModel().setFilmsFxObjectProperty(item);
                            controller.bindings();
                            Stage newStage = new Stage();
                            newStage.setScene(scene);
                            newStage.initModality(Modality.APPLICATION_MODAL);
                            newStage.showAndWait();

                    });
                }
            }
        });
    }

    private Button createButton(String path) {
        Button button = new Button();
        Image image = new Image(this.getClass().getResource(path).toString());
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        return button;
    }


    public void filterListFilmsComboBox() {
        this.listFilmsModel.filterFilmsList();
    }

    public void clearCategoryCombobox() {
        this.allFilmsCategoryComboBox.getSelectionModel().clearSelection();
    }

    public void clearDirectorCombobox() {
        this.allFilmsDirectorComboBox.getSelectionModel().clearSelection();
    }
}
