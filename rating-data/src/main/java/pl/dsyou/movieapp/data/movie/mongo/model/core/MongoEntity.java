package pl.dsyou.movieapp.data.movie.mongo.model.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
public abstract class MongoEntity {

    @Id
    @Getter
    @Setter
    private String id;
}
