package pl.dsyou.movieapp.data.movie.mongo.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

}
