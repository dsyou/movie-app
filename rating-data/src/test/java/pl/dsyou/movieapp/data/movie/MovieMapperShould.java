package pl.dsyou.movieapp.data.movie;

import org.junit.jupiter.api.Test;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieMapperShould {

    private String title = "Matrix";
    private String genre = "Action";
    private String productionDate = "01-01-1999";
    private float rank = 9.14f;

    private MovieMapper movieMapper = MovieMapper.INSTANCE;

    @Test
    public void mapToMovieDetailsFromMovie() throws ParseException {
        // given
        Movie movie = Movie.builder()
                .title(title)
                .genre(genre)
                .productionDate(new SimpleDateFormat("dd-MM-yyyy").parse(productionDate))
                .rank(rank)
                .build();

        // when
        MovieDetails movieDetails = movieMapper.toMovieDetails(movie);

        // then
        assertThat(movieDetails.getTitle()).isEqualTo(title);
        assertThat(movieDetails.getGenre()).isEqualTo(genre);
        assertThat(movieDetails.getProductionDate()).isEqualTo(productionDate);
        assertThat(movieDetails.getRank()).isEqualTo(rank);
    }

    @Test
    public void mapToMovieFromRegistration() throws ParseException {
        // given
        var movieRegistration = new MovieRegistration(title, genre, productionDate);

        // when
        Movie result = movieMapper.toMovie(movieRegistration);

        // then
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getGenre()).isEqualTo(genre);
        assertThat(result.getProductionDate()).isEqualTo(new SimpleDateFormat("dd-MM-yyyy").parse(productionDate));
        assertThat(result.getRank()).isEqualTo(0);
    }

    @Test
    public void mapToMovieFromUpdate() throws ParseException {
        // given
        Movie movie = Movie.builder()
                .title(title)
                .genre(genre)
                .productionDate(new SimpleDateFormat("dd-MM-yyyy").parse(productionDate))
                .rank(rank)
                .build();

        var movieUpdate = new MovieUpdate("Ice Age", "Animation", "01-01-2002");

        // when
        movieMapper.toMovie(movieUpdate, movie);

        // then
        assertThat(movie.getTitle()).isEqualTo("Ice Age");
        assertThat(movie.getGenre()).isEqualTo("Animation");
        assertThat(movie.getProductionDate()).isEqualTo(new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2002"));
        assertThat(movie.getRank()).isEqualTo(rank);
    }

}
