package pl.dsyou.movierating.rating.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import pl.dsyou.movierating.movie.domain.Movie;

@EqualsAndHashCode
public class Rate {
    @Id
    private long id;

    private AggregateReference<Movie, Long> movie;
    private float score; // todo BigDecimal

    public Rate(Long movieId) {
        this.movie = AggregateReference.to(movieId);
        this.score = 0.00f;
    }

    public void calculateAvgScore() {

    }
}
