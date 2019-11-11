package pl.dsyou.movieapp.data.movie;

import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;

import java.text.ParseException;
import java.util.List;

public interface MovieService {
    List<MovieDetails> getMovies();

    MovieDetails getMovie(String id);

    void addMovieRank(MovieRankAddition movieRankAddition, String id);

    MovieDetails createMovie(MovieRegistration movieRegistration) throws ParseException;

    MovieDetails editMovie(MovieUpdate movieUpdate, String id);

    void deleteMovie(String id);

}
