package pl.dsyou.movierating.movie.domain.exception;

import lombok.NonNull;
import pl.dsyou.movierating.common.exception.ElementNotFoundException;

public class MovieNotFoundException extends ElementNotFoundException {
    public MovieNotFoundException(@NonNull String id) {
        super("Movie with id = " + id + " cannot be found in the database");
    }
}
