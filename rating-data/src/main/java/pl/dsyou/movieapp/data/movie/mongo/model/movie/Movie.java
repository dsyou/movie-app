package pl.dsyou.movieapp.data.movie.mongo.model.movie;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.dsyou.movieapp.data.movie.mongo.model.core.MongoEntity;

import java.util.Date;

@Document(collation = "movie")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends MongoEntity {

    private String title;
    private String genre;

    private Date productionDate; // todo dj  () -> date  DD-MM-RRRR

    private float rank;
}
