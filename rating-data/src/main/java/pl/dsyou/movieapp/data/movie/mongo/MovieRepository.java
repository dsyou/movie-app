package pl.dsyou.movieapp.data.movie.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.dsyou.movieapp.data.movie.mongo.model.Movie;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    @Override
    Optional<Movie> findById(String id);

    @Override
    List<Movie> findAll();

}
