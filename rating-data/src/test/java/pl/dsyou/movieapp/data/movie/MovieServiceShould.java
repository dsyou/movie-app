package pl.dsyou.movieapp.data.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;
import pl.dsyou.movieapp.data.movie.mongo.model.MovieRepository;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.Movie;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.MovieRating;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    public void getMovie() throws ParseException {
        // given
        final Date productionDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
        final Movie movie = new Movie("Matrix", "Action", productionDate, new MovieRating(10f, Collections.singletonList(10f)));
        movie.setId("b846ca17-decf-4bb4-a127-ec1931dc35fa");
        when(movieRepository.findById("b846ca17-decf-4bb4-a127-ec1931dc35fa")).thenReturn(Optional.of(movie));

        // when
        final MovieDetails result = movieService.getMovie("b846ca17-decf-4bb4-a127-ec1931dc35fa");

        // then
        assertThat(result.getId()).isEqualTo("b846ca17-decf-4bb4-a127-ec1931dc35fa");
        assertThat(result.getProductionDate()).isEqualTo("01-01-1999");
        assertThat(result.getScore()).isEqualTo(10f);
        assertThat(result.getTitle()).isEqualTo("Matrix");
    }

    @Test
    @Disabled
    public void addMovieRank() {
        // given

        // when
        // todo arithmetic mean
        // check size on ranks , add a few values and count for them
        // check new value of score

        // then
    }

    @Test
    public void createMovie() throws ParseException {
        // given
        var movieRegistration = new MovieRegistration("Matrix", "Action", "01-01-1999");
        var productionDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
        var movie = new Movie("Matrix", "Action", productionDate, new MovieRating(10f, Collections.singletonList(10f)));
        when(movieRepository.save(any())).thenReturn(movie);

        // when
        final MovieDetails result = movieService.createMovie(movieRegistration);

        // then
        assertThat(result.getTitle()).isEqualTo("Matrix");
        assertThat(result.getGenre()).isEqualTo("Action");
        assertThat(result.getProductionDate()).isEqualTo("01-01-1999");
        assertThat(result.getScore()).isEqualTo(10f);
    }

    @Test
    public void editMovie() throws ParseException {
        // given
        var productionDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
        var movie = new Movie("Matrix", "Action", productionDate, new MovieRating(10f, Collections.singletonList(10f)));
        movie.setId("2f54589c-f5aa-481d-b4cc-ca4234876db3");
        var movieUpdate = new MovieUpdate("Terminator", "Porn", "01-01-1984");

        when(movieRepository.findById("2f54589c-f5aa-481d-b4cc-ca4234876db3")).thenReturn(Optional.of(movie));
        var updatedDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1984");
        when(movieRepository.save(any())).thenReturn(new Movie("Terminator", "Porn", updatedDate, new MovieRating(10f, Collections.singletonList(10f))));

        // when
        final MovieDetails result = movieService.editMovie(movieUpdate, "2f54589c-f5aa-481d-b4cc-ca4234876db3");

        // then
        assertThat(result.getTitle()).isEqualTo("Terminator");
        assertThat(result.getGenre()).isEqualTo("Porn");
        assertThat(result.getProductionDate()).isEqualTo("01-01-1984");
        assertThat(result.getScore()).isEqualTo(10f);
    }

    @Test
    public void deleteMovie() throws ParseException {
        // given
        var productionDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
        var movie = new Movie("Matrix", "Action", productionDate, new MovieRating(10f, Collections.singletonList(10f)));
        var movieId = "2f54589c-f5aa-481d-b4cc-ca4234876db3";
        movie.setId(movieId);
        doNothing().when(movieRepository).deleteById(movieId);

        // when
        movieService.deleteMovie(movieId);

        // then
        verify(movieRepository, times(1)).deleteById(movieId);
    }
}