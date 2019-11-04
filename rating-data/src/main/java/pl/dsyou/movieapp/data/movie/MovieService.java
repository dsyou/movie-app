package pl.dsyou.movieapp.data.movie;

import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.mongo.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies();

    void getMoviesRanks();

    void addMovieRank(String id, MovieRankAddition movieRankAddition);
}
