package pl.myFilms.Utilities.Converters;

import pl.myFilms.ModelFx.CategoryFx;
import pl.myFilms.database.models.Category;

public class ConverterCategory {

    public static CategoryFx convertToCategoryFx (Category category) {
        CategoryFx categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());
        return categoryFx;
    }
}
