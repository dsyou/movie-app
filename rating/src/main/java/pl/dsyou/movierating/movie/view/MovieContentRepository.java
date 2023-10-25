package pl.dsyou.movierating.movie.view;

import org.springframework.data.repository.CrudRepository;

interface MovieContentRepository extends CrudRepository<MovieContent, String> {
}
