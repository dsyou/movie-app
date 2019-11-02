package pl.dsyou.movieapp.data.movie.mongo.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private String title;
    private String genre;

    private String productionDate; // todo dj  () -> date

    private float rank; // todo dj from 0 to 10
}
