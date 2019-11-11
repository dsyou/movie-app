package pl.dsyou.movieapp.data.movie.mongo.model.movie;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.dsyou.movieapp.data.movie.mongo.model.core.MongoEntity;

@Document(collation = "movie")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends MongoEntity {

    private String title;
    private String genre;

    private String productionDate; // todo dj  () -> date  DD-MM-RRRR

    private float rank;
}
