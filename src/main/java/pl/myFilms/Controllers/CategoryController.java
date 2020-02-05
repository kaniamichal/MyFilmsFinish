package pl.myFilms.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeView;
import pl.myFilms.ModelFx.CategoryFx;
import pl.myFilms.ModelFx.CategoryModel;
import pl.myFilms.Utilities.DialogsUtilities;

public class CategoryController {

    @FXML
    private Button categoryAddButton;
    @FXML
    private javafx.scene.control.TextField categoryTextField;
    @FXML
    private ComboBox<CategoryFx> categoryComboBox;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private Button editCategoryButton;
    @FXML
    private TreeView<String> categoryTreeView;

    private CategoryModel categoryModel;


    @FXML
    public void initialize() {
        this.categoryModel = new CategoryModel();
        this.categoryModel.init();
        this.categoryComboBox.setItems(this.categoryModel.getCategoryList());
        this.categoryTreeView.setRoot(this.categoryModel.getRoot());
        initRelations();
    }


    private void initRelations() {
        this.categoryAddButton.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        this.deleteCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
        this.editCategoryButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
    }


    public void addCategoryOnAction() {
        categoryModel.saveCategoryInDatabase(categoryTextField.getText());
        categoryTextField.clear();
    }

    public void deleteCategoryButton() {
        this.categoryModel.deleteCategoryById();
    }


    public void actionCategoryComboBox() {
        this.categoryModel.setCategory(this.categoryComboBox.getSelectionModel().getSelectedItem());
    }

    public void editCategory() {
        String newCategoryName = DialogsUtilities.editDialog(this.categoryModel.getCategory().getName());
        if (newCategoryName != null)
            this.categoryModel.editCategoryInDatabase(newCategoryName);
    }
}
