package pl.dsyou.movieapp.data.movie;

import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;
import pl.dsyou.movieapp.data.movie.exception.MovieNotFoundException;
import pl.dsyou.movieapp.data.movie.mongo.model.MovieRepository;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.Movie;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.MovieRating;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;

    @Override
    public MovieDetails getMovie(String id) {
        log.info("Accessing movie by id: {}", id);
        return movieRepository.findById(id)
                .map(movieMapper::toMovieDetails)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    private Movie getMovieById(String id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public List<MovieDetails> getMovies() {
        log.info("Accessing all movies");
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieDetails)
                .collect(Collectors.toList());
    }

    @Override
    public float addMovieRank(MovieRankAddition movieRankAddition, String movieId) {
        log.info("Adding rank:{} to movie with id:{}", movieRankAddition.getRank(), movieId);
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        MovieRating movieRating = movie.getMovieRating();

        List<Float> ranks = movieRating.getRanks();
        ranks.add(movieRankAddition.getRank());

        float sumOfMovieRanks;
        if (ranks.get(0) == 0f && ranks.size() == 2) {
            sumOfMovieRanks = ranks.get(1);
        } else {
            sumOfMovieRanks = countAverageOfMovieRanks(ranks);
        }
        log.debug("Calculated arithmetic average:{} , for movie with id:{}", sumOfMovieRanks, movieId);
        float roundedSumOfMovieRanks = round(sumOfMovieRanks, 2);

        movieRating.setScore(roundedSumOfMovieRanks);

        movieRepository.save(movie);
        return roundedSumOfMovieRanks;
    }

    private float countAverageOfMovieRanks(List<Float> ranks) {
        float sum = 0;
        for (Float rank : ranks) {
            sum += rank;
        }
        return sum / (ranks.size() - 1);
    }

    private float round(float sumOfRanks, int decimalPlace) {
        return BigDecimal.valueOf(sumOfRanks).setScale(decimalPlace, RoundingMode.UP).floatValue();
    }

    @Override
    public MovieDetails createMovie(MovieRegistration movieRegistration) {
        log.info("Creating new movie with title:{}", movieRegistration.getTitle());
        Movie movie = movieMapper.toMovie(movieRegistration);
        movie.setMovieRating(new MovieRating(0, Collections.singletonList(0f)));

        return Option.of(movieRepository.save(movie))
                .map(movieMapper::toMovieDetails).get();
    }

    @Override
    public MovieDetails editMovie(MovieUpdate movieUpdate, String id) {
        log.info("Editing movie with id:{}", id);
        Movie movie = getMovieById(id);
        movieMapper.toMovie(movieUpdate, movie);

        return Option.of(movieRepository.save(movie))
                .map(movieMapper::toMovieDetails)
                .get();
    }

    @Override
    public void deleteMovie(String movieId) {
        log.info("Deleting movie with id:{}", movieId);
        movieRepository.deleteById(movieId);
    }

}
