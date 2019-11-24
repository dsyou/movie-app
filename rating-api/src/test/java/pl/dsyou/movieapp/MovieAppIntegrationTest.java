package pl.dsyou.movieapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieAppIntegrationTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private String createUrlWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    /**
     * 1/ Create Movie
     * 2/ Check movie -> score should be 0
     * 3/ Add rank 1
     * 4/ Check rank 2
     * 5/ Add rank 2
     * 6/ Check rank 2
     * 7/ Add rank 3
     * 8/ Check rank 3
     * 9/ Final assertion
     */
    @Test
    public void addingMovieRanksShouldWorkThroughAllLayers() {
        HttpEntity<MovieRegistration> request1 = new HttpEntity<>(new MovieRegistration("Matrix", "Action", "01-01-1999"));
        ResponseEntity<MovieDetails> response1 = restTemplate.exchange(
                createUrlWithPort("/api/v1/movies"), HttpMethod.POST, request1, MovieDetails.class);

        MovieDetails result1 = response1.getBody();
        assertThat(result1).isNotNull();

        final var movieId = result1.getId();

        assertThat(movieId).isNotEmpty();
        assertThat(result1.getTitle()).isEqualTo("Matrix");
        assertThat(result1.getGenre()).isEqualTo("Action");
        assertThat(result1.getProductionDate()).isEqualTo("01-01-1999");
        assertThat(result1.getScore()).isEqualTo(0);

        // req 2
        HttpEntity<MovieRankAddition> request2 = new HttpEntity<>(new MovieRankAddition(8.0f));
        ResponseEntity<Float> response2 = restTemplate.exchange(
                createUrlWithPort("/api/v1/movies/" + movieId + "/ranks"), HttpMethod.POST, request2, Float.class);


        assertThat(response2.getBody()).isEqualTo(8.0f);

        // req 3
        HttpEntity<MovieRankAddition> request3 = new HttpEntity<>(new MovieRankAddition(7.0f));
        ResponseEntity<Float> response3 = restTemplate.exchange(
                createUrlWithPort("/api/v1/movies/" + movieId + "/ranks"), HttpMethod.POST, request3, Float.class);

        assertThat(response3.getBody()).isEqualTo(7.5f);

        // req 4
        HttpEntity<MovieRankAddition> request4 = new HttpEntity<>(new MovieRankAddition(5.67f));
        ResponseEntity<Float> response4 = restTemplate.exchange(
                createUrlWithPort("/api/v1/movies/" + movieId + "/ranks"), HttpMethod.POST, request4, Float.class);

        assertThat(response4.getBody()).isEqualTo(6.89f);

        // req 5
        ResponseEntity<MovieDetails> response5 = restTemplate.getForEntity(createUrlWithPort("/api/v1/movies/" + movieId), MovieDetails.class);

        assertThat(response5.getBody().getId()).isEqualTo(movieId);
        assertThat(response5.getBody().getTitle()).isEqualTo("Matrix");
        assertThat(response5.getBody().getProductionDate()).isEqualTo("01-01-1999");
        assertThat(response5.getBody().getGenre()).isEqualTo("Action");
        assertThat(response5.getBody().getScore()).isEqualTo(6.89f);
    }

}