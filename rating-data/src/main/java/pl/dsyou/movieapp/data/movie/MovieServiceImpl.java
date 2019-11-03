package pl.dsyou.movieapp.data.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dsyou.movieapp.data.movie.mongo.MovieRepository;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public void getMovies() {

    }

    @Override
    public void getMoviesRanks() {

    }

    @Override
    public void addMovieRank() {

    }
}
