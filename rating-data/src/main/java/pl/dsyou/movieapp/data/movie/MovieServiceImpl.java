package pl.dsyou.movieapp.data.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.mongo.MovieRepository;
import pl.dsyou.movieapp.data.movie.mongo.model.Movie;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public void getMoviesRanks() {
        // get only movies ranks
    }

    @Override
    @Transactional
    public void addMovieRank(String id, MovieRankAddition movieRankAddition) {
        // todo crate Controller Advice
        Movie movie = movieRepository.findById(id).orElseThrow(null);
        movie.setRank(movieRankAddition.getRank());
        movieRepository.save(movie);
    }
}
