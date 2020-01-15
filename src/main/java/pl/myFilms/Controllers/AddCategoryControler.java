package pl.myFilms.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pl.myFilms.ModelFx.CategoryFx;
import pl.myFilms.ModelFx.CategoryModel;
import pl.myFilms.Utilities.Exception.ApplicationException;
import pl.myFilms.database.dao.CommonDao;

public class AddCategoryControler {

    @FXML
    private Button categoryAddButton;
    @FXML
    private javafx.scene.control.TextField categoryTextField;
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    private CategoryModel categoryModel;
    @FXML
    private Button deleteCategoryButton;

    @FXML
    public void initialize() throws ApplicationException {
        this.categoryModel = new CategoryModel();
        this.categoryModel.init();
        this.categoryComboBox.setItems(this.categoryModel.getCategoryFxObservableList());
        initRelations();
    }


    private void initRelations() {
        this.categoryAddButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryFxObjectPropertyProperty().isNull());
    }


    public void addCategoryOnAction() {
        try {
            categoryModel.saveCategoryInDatabase(categoryTextField.getText());
            categoryTextField.clear();
        } catch (ApplicationException e) {
            CommonDao.LOGGER.warn(e.getMessage());
            //e.printStackTrace();
        }

    }

    public void deleteCategoryButton() throws ApplicationException {
        this.categoryModel.deleteCategoryById();
    }


    public void actionCategoryComboBox() {
        System.out.println("actionCategoryComboBox actionCategoryComboBox actionCategoryComboBox");
        this.categoryModel.setCategoryFxObjectProperty(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }
}
