package pl.dsyou.movieapp.data.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;
import pl.dsyou.movieapp.data.movie.exception.MovieNotFoundException;
import pl.dsyou.movieapp.data.movie.mongo.MovieRepository;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;

    @Override
    public MovieDetails getMovie(String id) {
        return movieRepository.findById(id)
                .map(movieMapper::toMovieDetails)
                .orElseThrow(MovieNotFoundException::new);
    }

    @Override
    public List<MovieDetails> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieDetails)
                .collect(Collectors.toList());
    }

    @Override
    public void addMovieRank(long id, MovieRankAddition movieRankAddition) {
        // todo crate Controller Advice
        Movie movie = movieRepository.findById(String.valueOf(id)).orElseThrow(MovieNotFoundException::new);
        movie.setRank(movieRankAddition.getRank());
        movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void createMovie(MovieRegistration movieRegistration) {
        final Movie movie = movieMapper.toMovie(movieRegistration);
        movieRepository.save(movie);
    }

    @Override
    public void editMovie(MovieUpdate movieUpdate, String id) {
        final Movie movie = movieMapper.toMovie(movieUpdate);
        movieRepository.findById(id);
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(String movieId) {
        movieRepository.deleteById(String.valueOf(movieId));
    }

}
