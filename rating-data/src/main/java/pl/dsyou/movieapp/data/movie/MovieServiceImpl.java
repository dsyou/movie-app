package pl.dsyou.movieapp.data.movie;

import lombok.RequiredArgsConstructor;
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
import java.util.Optional;
import java.util.Set;
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
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    private Movie getMovieById(String id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public List<MovieDetails> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieDetails)
                .collect(Collectors.toList());
    }

    @Override
    public float addMovieRank(MovieRankAddition movieRankAddition, String movieId) {
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
        Movie movie = movieMapper.toMovie(movieRegistration);
        movie.setMovieRating(new MovieRating(0, Collections.singletonList(0f)));
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
