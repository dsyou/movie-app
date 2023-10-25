package pl.dsyou.movierating.movie.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.Date;

// @AggregateRoot
// @Getter(Package)
// @Table(MovieTableNames.Movie)
// @NoArgsConstructor(access = Package)
@EqualsAndHashCode
public class Movie {
    @Getter
    @Id
    private long id;

    @Embedded.Empty
    private Description description;

    private Movie(String title,
                  String genre,
                  Date productionDate) {

        this.description = new Description(title, genre, productionDate);

    }

    @PersistenceCreator
    public static Movie of(String title,
                           String genre,
                           Date productionDate) {


        return new Movie(title, genre, productionDate);
    }

}
