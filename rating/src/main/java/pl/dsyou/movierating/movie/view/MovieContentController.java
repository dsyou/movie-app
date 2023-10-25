package pl.dsyou.movierating.movie.view;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.dsyou.movierating.movie.domain.exception.MovieNotFoundException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("movies")
@RequiredArgsConstructor
class MovieContentController {

    private final MovieContentRepository repository;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieContent getMovie(@PathVariable String id) {
        log.info("Accessing movie by id: {}", id);
        return repository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieContent> getMovies() {
        log.info("Accessing all movies");
        return (List<MovieContent>) repository.findAll();
    }

}
