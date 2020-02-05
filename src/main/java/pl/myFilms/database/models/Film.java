package pl.myFilms.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "FILMS")
public class Film implements BaseModel {

    public static final String DIRECTOR_ID = "AUTHOR_ID";
    public static final String CATEGORY_ID = "CATEGORY_ID";

    public Film() {
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = DIRECTOR_ID, foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false) //
    private Director director;
    @DatabaseField(columnName = CATEGORY_ID,foreign = true,  foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false) //
    private Category category;

    @DatabaseField(columnName = "TITLE", canBeNull = false)
    private String title;

    @DatabaseField(columnName = "DESCRIPTION")
    private String description;

    @DatabaseField(columnName = "RELEASE_DATE")
    private Date releaseDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
