package pl.myFilms.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "DIRECTOR")
public class Director implements BaseModel {

    public Director() {

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String nameAndSurname;

    @ForeignCollectionField(eager = true)
    private ForeignCollectionField films;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public ForeignCollectionField getFilms() {
        return films;
    }

    public void setFilms(ForeignCollectionField films) {
        this.films = films;
    }
}
