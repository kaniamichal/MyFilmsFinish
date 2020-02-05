package pl.myFilms.ModelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import pl.myFilms.Utilities.Converters.ConverterCategory;
import pl.myFilms.Utilities.DialogsUtilities;
import pl.myFilms.Utilities.Exception.ApplicationException;
import pl.myFilms.database.dao.CategoryDao;
import pl.myFilms.database.dao.FilmDao;
import pl.myFilms.database.models.Category;
import pl.myFilms.database.models.Film;

import java.util.List;

public class CategoryModel {

    private ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList(); //podłączony do Combobox
    private ObjectProperty<CategoryFx> category = new SimpleObjectProperty<>(); //przytrzymuje aktualną kategorię w combobox
    private TreeItem<String> root = new TreeItem<>();


    public void init() {
        try {
            CategoryDao categoryDao = new CategoryDao();
            List<Category> categories = categoryDao.queryForAll(Category.class);
            initCategoryList(categories);
            initRoot(categories);
        } catch (ApplicationException e) {
            DialogsUtilities.initDialog(e.getMessage());
        }
    }

    private void initCategoryList(List<Category> categories) {
        this.categoryList.clear();
        categories.forEach(c -> {
            CategoryFx categoryFx = ConverterCategory.convertToCategoryFx(c);
            this.categoryList.add(categoryFx);
        });
    }


    private void initRoot(List<Category> categories) {
        this.root.getChildren().clear();
        categories.forEach(c -> {
            TreeItem<String> categoryItem = new TreeItem<>(c.getName());
            c.getFilms().forEach(b -> {
                categoryItem.getChildren().add(new TreeItem<>(b.getTitle()));
            });
            root.getChildren().add(categoryItem);
        });

    }


    public void deleteCategoryById() {
        try {
            CategoryDao categoryDao = new CategoryDao();
            categoryDao.deleteById(Category.class, category.getValue().getId());
            FilmDao filmDao = new FilmDao();
            filmDao.deleteByColumnName(Film.CATEGORY_ID, category.getValue().getId());
            init();
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }

    public void saveCategoryInDatabase(String name) {
        try {
            CategoryDao categoryDao = new CategoryDao();
            Category category = new Category();
            category.setName(name);
            categoryDao.creatOrUpdate(category);
            init();
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }

    public void editCategoryInDatabase(String name) {
        try {
            CategoryDao categoryDao = new CategoryDao();
            Category tempCategory = categoryDao.findById(Category.class, getCategory().getId());
            tempCategory.setName(name);
            categoryDao.creatOrUpdate(tempCategory);
            init();
        } catch (ApplicationException e) {
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }

    public TreeItem<String> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<String> root) {
        this.root = root;
    }

    public ObservableList<CategoryFx> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ObservableList<CategoryFx> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryFx getCategory() {
        return category.get();
    }

    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }

    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }
}

