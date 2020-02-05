package pl.myFilms.Utilities.Converters;

import pl.myFilms.ModelFx.FilmsFx;
import pl.myFilms.Utilities.Utils;
import pl.myFilms.database.models.Film;

public class ConverterFilm {

    public static Film convertToFilm(FilmsFx filmsFx) {
        Film film = new Film();
        film.setId(filmsFx.getId());
        film.setDescription(filmsFx.getDescription());
        film.setTitle(filmsFx.getTitle());
        film.setReleaseDate(Utils.convertToDate(filmsFx.getYear()));
        return film;
    }

    public static FilmsFx converterToFilmFx (Film film) {
        FilmsFx filmsFx = new FilmsFx();
        filmsFx.setId(film.getId());
        filmsFx.setDescription(film.getDescription());
        filmsFx.setTitle(film.getTitle());
        filmsFx.setYear(Utils.convertToLocalDate(film.getReleaseDate()));
        filmsFx.setDirectorFxFilmsFx(ConverterDirector.convertToDirectorFx(film.getDirector()));
        filmsFx.setCategoryFxFilmsFx(ConverterCategory.convertToCategoryFx(film.getCategory()));
        return filmsFx;
    }
}
