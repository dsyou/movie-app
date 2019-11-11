package pl.dsyou.movieapp.data.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;
import pl.dsyou.movieapp.data.movie.mongo.MovieRepository;
import pl.dsyou.movieapp.data.movie.mongo.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;
    private final MovieRepository movieRepository;

    @Override
    public List<MovieDetails> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieDetails)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addMovieRank(long id, MovieRankAddition movieRankAddition) {
        // todo crate Controller Advice
        Movie movie = movieRepository.findById(String.valueOf(id)).orElseThrow(null);
        movie.setRank(movieRankAddition.getRank());
        movieRepository.save(movie);
    }

    @Override
    public void createMovie(MovieRegistration movieRegistration) {

    }

    @Override
    public void editMovie(MovieUpdate movieUpdate) {

    }

    @Override
    public void deleteMovie(long id) {

    }
}
