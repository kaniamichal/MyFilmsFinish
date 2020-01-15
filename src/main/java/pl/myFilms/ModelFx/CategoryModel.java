package pl.myFilms.ModelFx;

import pl.myFilms.Utilities.Exception.ApplicationException;
import pl.myFilms.database.dao.CategoryDao;
import pl.myFilms.database.dbutils.DbManager;
import pl.myFilms.database.models.Category;

public class CategoryModel {

    public void saveCategoryInDatabase(String name) throws ApplicationException {
        CategoryDao categoryDao = new CategoryDao(DbManager.getConnectionSource());
        Category category = new Category();
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        DbManager.closeConnectionSource();


    }
}
