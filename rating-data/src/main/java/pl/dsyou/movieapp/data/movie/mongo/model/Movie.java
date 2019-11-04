package pl.dsyou.movieapp.data.movie.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "movies")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    private String id;
    private String title;
    private String genre;

    private String productionDate; // todo dj  () -> date

    private float rank; // todo dj from 0 to 10
}
