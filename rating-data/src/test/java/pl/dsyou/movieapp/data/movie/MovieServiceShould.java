package pl.dsyou.movieapp.data.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.mongo.MovieRepository;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.Movie;

import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceShould {

    private MovieMapper movieMapper = MovieMapper.INSTANCE;

    @Mock
    private MovieRepository movieRepository;

    private MovieServiceImpl movieService;

    @BeforeEach
    public void setUp() {
        movieService = new MovieServiceImpl(movieMapper, movieRepository);
    }

    @Test
    public void getMovies() {
        // given
        final Movie movie1 = new Movie();
        final Movie movie2 = new Movie();
        final Movie movie3 = new Movie();
        when(movieRepository.findAll()).thenReturn(List.of(movie1, movie2, movie3));

        // when
        List<MovieDetails> result = movieService.getMovies();

        // then
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void getMovie() {
        // given

        // when
//        movieService.getMovie("ADD KEY");

        // then
    }

    @Test
    public void addMovieRank() {
        // given

        // when
//        movieService.addMovieRank();

        // then
    }

    @Test
    public void createMovie() throws ParseException {
        // given

        // when

//        movieService.createMovie(new MovieRegistration("Matrix", "Action", sDate1));

        // then
    }

    @Test
    public void editMovie() {
        // given

        // when
//        movieService.editMovie();

        // then
    }

    @Test
    public void deleteMovie() {
        // given

        // when
//        movieService.deleteMovie();

        // then
    }
}