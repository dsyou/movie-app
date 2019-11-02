package pl.dsyou.movieapp.data.movie;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.mongo.model.Movie;

public class MovieMapperShould {

    private String title = "Matrix";
    private String genre = "Action";
    private String productionDate = "01.01.1999";
    private float rank = 9.14f;

    private MovieMapper movieMapper = MovieMapper.INSTANCE;

    @Test
    public void toMovieDetails() {
        // given
        Movie movie = Movie.builder()
                .title(title)
                .genre(genre)
                .productionDate(productionDate)
                .rank(rank)
                .build();

        // when
        MovieDetails movieDetails = movieMapper.toMovieDetails(movie);

        // then
        assertThat(movieDetails.getTitle()).isEqualTo("");
        assertThat(movieDetails.getGenre()).isEqualTo("");
        assertThat(movieDetails.getProductionDate()).isEqualTo("");
        assertThat(movieDetails.getScore()).isEqualTo("");
    }
}
