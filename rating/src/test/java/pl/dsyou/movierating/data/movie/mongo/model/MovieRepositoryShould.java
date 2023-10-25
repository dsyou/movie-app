package pl.dsyou.movierating.data.movie.mongo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.dsyou.movierating.movie.domain.exception.MovieNotFoundException;
//import pl.dsyou.movierating.movie.domain.mongo.model.movie.MovieRepository;
//import pl.dsyou.movierating.data.movie.mongo.model.movie.Movie;
//import pl.dsyou.movierating.data.movie.mongo.model.movie.MovieRating;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MovieRepositoryShould {

//    @Autowired
//    private MovieRepository movieRepository;
//
//    @BeforeEach
//    void setUp() {
//        movieRepository.deleteAll();
//    }
//
//    @Test
//    public void createMovies() throws ParseException {
//        // given
//        var movieRating = new MovieRating(10f, Collections.singletonList(10f));
//        final Date productionDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
//        final Movie movie = new Movie("Matrix", "Action", productionDate, movieRating);
//
//        //  when
//        final var result = movieRepository.save(movie);
//
//        // then
//        assertThat(result.getId()).isNotBlank();
//        assertThat(result.getTitle()).isEqualTo("Matrix");
//        assertThat(result.getGenre()).isEqualTo("Action");
//        assertThat(result.getMovieRating().getScore()).isEqualTo(10f);
//        assertThat(result.getProductionDate()).isEqualTo(productionDate);
//    }
//
//    @Test
//    public void findMovieById() throws ParseException {
//        // given
//        var movieRating = new MovieRating(10f, Collections.singletonList(10f));
//        var productionDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
//        var movie = new Movie("Matrix", "Action", productionDate, movieRating);
//        String movieId = "cacb9065-0f23-46b4-9506-8a8cda6c17f3";
//        movie.setId(movieId);
//        movieRepository.save(movie);
//
//        // when
//        var result = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
//
//        // then
//        assertThat(result).isNotNull();
//        assertThat(result.getTitle()).isEqualTo("Matrix");
//        assertThat(result.getGenre()).isEqualTo("Action");
//        assertThat(result.getMovieRating().getScore()).isEqualTo(10f);
//        assertThat(result.getProductionDate()).isEqualTo(productionDate);
//    }
//
//    @Test
//    public void notFindMovieByIncorrectId() throws ParseException {
//        // given
//        var movieRating = new MovieRating(10f, Collections.singletonList(10f));
//        var productionDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
//        var movie = new Movie("Matrix", "Action", productionDate, movieRating);
//        movieRepository.save(movie);
//
//        // expect & when
//        assertThatThrownBy(() -> movieRepository.findById("INCORRECT_ID").orElseThrow(() -> new MovieNotFoundException("")))
//                .isInstanceOf(MovieNotFoundException.class);
//    }
//
//    @Test
//    public void findMovies() throws ParseException {
//        // given
//        var productionDate1 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
//        var movieRating1 = new MovieRating(10f, Collections.singletonList(10f));
//        var movie1 = new Movie("Matrix I", "Action", productionDate1, movieRating1);
//        movieRepository.save(movie1);
//
//        var productionDate2 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2003");
//        var movieRating2 = new MovieRating(8f, Collections.singletonList(8f));
//        var movie2 = new Movie("Matrix II", "Action", productionDate2, movieRating2);
//        movieRepository.save(movie2);
//
//        var productionDate3 = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2004");
//        var movieRating3 = new MovieRating(7f, Collections.singletonList(7f));
//        var movie3 = new Movie("Matrix III", "Action", productionDate3, movieRating3);
//        movieRepository.save(movie3);
//
//        // when
//        final List<Movie> result = movieRepository.findAll();
//
//        // then
//        assertThat(result.size()).isEqualTo(3);
//
//        assertThat(result.get(0)).satisfies(m -> {
//            assertThat(m.getTitle()).isEqualTo("Matrix I");
//            assertThat(m.getGenre()).isEqualTo("Action");
//            assertThat(m.getMovieRating().getScore()).isEqualTo(10f);
//            assertThat(m.getProductionDate()).isEqualTo(productionDate1);
//        });
//
//        assertThat(result.get(1)).satisfies(m -> {
//            assertThat(m.getTitle()).isEqualTo("Matrix II");
//            assertThat(m.getGenre()).isEqualTo("Action");
//            assertThat(m.getMovieRating().getScore()).isEqualTo(8f);
//            assertThat(m.getProductionDate()).isEqualTo(productionDate2);
//        });
//
//        assertThat(result.get(2)).satisfies(m -> {
//            assertThat(m.getTitle()).isEqualTo("Matrix III");
//            assertThat(m.getGenre()).isEqualTo("Action");
//            assertThat(m.getMovieRating().getScore()).isEqualTo(7f);
//            assertThat(m.getProductionDate()).isEqualTo(productionDate3);
//        });
//    }
//
//    @Test
//    public void updateMovies() throws ParseException {
//        // given
//        var movieRating = new MovieRating(10f, Collections.singletonList(10f));
//        var productionDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
//        var movie = new Movie("Matrix I", "Action", productionDate, movieRating);
//        final var id = movieRepository.save(movie).getId();
//
//        // when
//        final var foundMovie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
//        foundMovie.setGenre("Comedy");
//
//        final var result = movieRepository.save(foundMovie);
//
//        // then
//        assertThat(result.getGenre()).isEqualTo("Comedy");
//        assertThat(result.getTitle()).isEqualTo("Matrix I");
//        assertThat(result.getMovieRating().getScore()).isEqualTo(10f);
//        assertThat(result.getProductionDate()).isEqualTo(productionDate);
//    }
//
//    @Test
//    public void deleteMovieById() throws ParseException {
//        // given
//        var movieRating = new MovieRating(10f, Collections.singletonList(10f));
//        var productionDate = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1999");
//        var movie = new Movie("Matrix I", "Action", productionDate, movieRating);
//        final var id = movieRepository.save(movie).getId();
//
//        // when
//        movieRepository.deleteById(id);
//
//        // then
//        assertThat(movieRepository.findById(id)).isEmpty();
//    }
}