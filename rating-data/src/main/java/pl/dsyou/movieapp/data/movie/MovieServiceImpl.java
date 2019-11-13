package pl.dsyou.movieapp.data.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;
import pl.dsyou.movieapp.data.movie.exception.MovieNotFoundException;
import pl.dsyou.movieapp.data.movie.mongo.MovieRepository;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.Movie;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
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

    private Movie getMovieById(String id) {
        return movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
    }

    @Override
    public List<MovieDetails> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieDetails)
                .collect(Collectors.toList());
    }

    @Override
    public void addMovieRank(MovieRankAddition movieRankAddition, String movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);


        movie.setRank(movieRankAddition.getRank());
        movieRepository.save(movie);
    }

    @Override
    public MovieDetails createMovie(MovieRegistration movieRegistration) throws ParseException {
        final Movie movie = movieMapper.toMovie(movieRegistration);
        return Optional.of(movieRepository.save(movie))
                .map(movieMapper::toMovieDetails).get();
    }

    @Override
    public MovieDetails editMovie(MovieUpdate movieUpdate, String id) {
        Movie movie = getMovieById(id);
        movieMapper.toMovie(movieUpdate, movie);

        return Optional.of(movieRepository.save(movie))
                .map(movieMapper::toMovieDetails)
                .get();
    }

    @Override
    public void deleteMovie(String movieId) {
        movieRepository.deleteById(movieId);
    }

}
