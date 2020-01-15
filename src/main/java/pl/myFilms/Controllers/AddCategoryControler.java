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
    public void initialize(){
        this.categoryModel = new CategoryModel();
        initRelations();
    }

    private void initRelations() {
        categoryAddButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
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

    
}
