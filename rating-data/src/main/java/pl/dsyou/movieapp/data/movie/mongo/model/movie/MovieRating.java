package pl.dsyou.movieapp.data.movie.mongo.model.movie;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRating {
    private float score;
    private List<Float> ranks;
}
