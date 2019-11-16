package pl.dsyou.movieapp.data.movie.exception;

public class MovieDateParseException extends RuntimeException {
    public MovieDateParseException(String productionDate) {
        super("Cannot parse movie with productionDate: " + productionDate);
    }
}
