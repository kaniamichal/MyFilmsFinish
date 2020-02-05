package pl.myFilms.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.myFilms.ModelFx.DirectorFx;
import pl.myFilms.ModelFx.DirectorModel;

public class DirectorController {

    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button addDirectorButton;
    @FXML
    private TableView<DirectorFx> tableViewDirector;
    @FXML
    private TableColumn<DirectorFx, String> nameColumn;
    @FXML
    private TableColumn<DirectorFx, String> surnameColumn;
    @FXML
    private MenuItem deleteDirectorTableViewItem;


    private DirectorModel directorModel;

    public void initialize() {
        this.directorModel = new DirectorModel();
        this.directorModel.init();
        bindings();
        bindingDirectorTableView();
    }

    private void bindingDirectorTableView() {
        this.tableViewDirector.setItems(this.directorModel.getDirectorFxObservableList());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        //aktywowanie komórek w kolumnach

        this.nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tableViewDirector.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.directorModel.setDirectorFxObjectPropertyEdit(newValue);
        });
    }

    private void bindings() { //OFF activity
        this.directorModel.directorFxObjectPropertyProperty().get().nameProperty().bind(this.nameTextField.textProperty());
        this.directorModel.directorFxObjectPropertyProperty().get().surnameProperty().bind(this.surnameTextField.textProperty());
        this.addDirectorButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty().or(this.surnameTextField.textProperty().isEmpty()));
        this.deleteDirectorTableViewItem.disableProperty().bind(this.tableViewDirector.getSelectionModel().selectedItemProperty().isNull());
    }

    public void AddAuthorButton() {
        this.directorModel.saveAuthorInDatabase();
        this.nameTextField.clear();
        this.surnameTextField.clear();
    }

    public void onEditCommitName(TableColumn.CellEditEvent<DirectorFx, String> directorFxStringCellEditEvent) {
        this.directorModel.getDirectorFxObjectPropertyEdit().setName(directorFxStringCellEditEvent.getNewValue());
        updateInDataBase();
    }

    public void onEditCommitSurname(TableColumn.CellEditEvent<DirectorFx, String> directorFxStringCellEditEvent) {
        this.directorModel.getDirectorFxObjectPropertyEdit().setSurname(directorFxStringCellEditEvent.getNewValue());
        updateInDataBase();
    }

    private void updateInDataBase() {
        this.directorModel.saveAuthorEditInDatabase();
    }

    public void deleteDirectorTableView() {
        this.directorModel.deleteDirectorInDataBaseById();
    }
}
