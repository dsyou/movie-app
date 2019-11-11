package pl.dsyou.movieapp.data.movie;

import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;

import java.util.List;

public interface MovieService {
    List<MovieDetails> getMovies();

    void addMovieRank(long id, MovieRankAddition movieRankAddition);

    void createMovie(MovieRegistration movieRegistration);

    void editMovie(MovieUpdate movieUpdate, String id);

    void deleteMovie(String id);

    MovieDetails getMovie(String id);
}
