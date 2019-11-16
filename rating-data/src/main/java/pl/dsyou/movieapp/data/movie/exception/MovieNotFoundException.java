package pl.dsyou.movieapp.data.movie.exception;

import lombok.NonNull;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(@NonNull String id) {
        super("Movie with id = " + id + " cannot be found in the database");
    }
}
