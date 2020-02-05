package pl.myFilms.Utilities.Converters;

import pl.myFilms.ModelFx.DirectorFx;
import pl.myFilms.database.models.Director;

public class ConverterDirector {

    public static Director convertToDirector(DirectorFx directorFx) {
        Director director = new Director();
        director.setId(directorFx.getId());
        director.setName(directorFx.getName());
        director.setSurname(directorFx.getSurname());
        return director;
    }

    public static DirectorFx convertToDirectorFx (Director director){
        DirectorFx directorFx = new DirectorFx();
        directorFx.setId(director.getId());
        directorFx.setName(director.getName());
        directorFx.setSurname(director.getSurname());
        return directorFx;
    }
}
