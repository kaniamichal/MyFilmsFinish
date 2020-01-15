package pl.myFilms.ModelFx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.myFilms.Utilities.Exception.ApplicationException;
import pl.myFilms.database.dao.CategoryDao;
import pl.myFilms.database.dbutils.DbManager;
import pl.myFilms.database.models.Category;

import java.util.List;

public class CategoryModel {

    private ObservableList<CategoryFx> categoryFxObservableList = FXCollections.observableArrayList(); //podłączony do Combobox
    private ObjectProperty<CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>(); //przytrzymuje aktualną kategorię w combobox


    public void init() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        List<Category> categories = categoryDao.queryForAll(Category.class);
        this.categoryFxObservableList.clear();
        categories.forEach(c -> {
            CategoryFx categoryFx = new CategoryFx();
            categoryFx.setId(c.getId());
            categoryFx.setName(c.getName());
            this.categoryFxObservableList.add(categoryFx);
        });
        DbManager.closeConnectionSource();
    }

    public void deleteCategoryById() throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        categoryDao.deleteById(Category.class, categoryFxObjectProperty.getValue().getId());
        DbManager.closeConnectionSource();
        init();
    }

    public void saveCategoryInDatabase(String name) throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        DbManager.closeConnectionSource();
        init();
    }

    public ObservableList<CategoryFx> getCategoryFxObservableList() {
        return categoryFxObservableList;
    }

    public void setCategoryFxObservableList(ObservableList<CategoryFx> categoryFxObservableList) {
        this.categoryFxObservableList = categoryFxObservableList;
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

/*
    public void setCategoryFxObjectProperty(ObjectProperty<CategoryFx> selectedIndex) {
      this.categoryFxObjectProperty = selectedIndex;
      }*/


}

