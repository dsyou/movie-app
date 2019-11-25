package pl.dsyou.movieapp.data.movie.mongo.model.movie;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.dsyou.movieapp.data.movie.mongo.model.core.MongoEntity;

import java.util.Date;

@Document
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends MongoEntity {

    private String title;
    private String genre;

    private Date productionDate;

    private MovieRating movieRating;
}
