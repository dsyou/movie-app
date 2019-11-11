package pl.dsyou.movieapp.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.dsyou.movieapp.configuration.ApiVersion;
import pl.dsyou.movieapp.data.movie.MovieService;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;

import javax.validation.Valid;
import java.util.List;

@RestController
@ApiVersion(1)
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MoviesApiController {

    private final MovieService movieService;

    @GetMapping("{id}")
    public MovieDetails getMovie(@PathVariable String id) {
        return movieService.getMovie(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDetails> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createMovie(@RequestBody @Valid MovieRegistration movieRegistration) {
        movieService.createMovie(movieRegistration);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editMovie(@PathVariable String id, @RequestBody @Valid MovieUpdate movieUpdate) {
        movieService.editMovie(movieUpdate, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable("id") String id) {
        movieService.deleteMovie(id);
    }

    @PostMapping("{id}/ranks")
    @ResponseStatus(HttpStatus.OK)
    public void addMovieRank(
            @PathVariable("id") String id, @RequestBody @Valid MovieRankAddition movieRankAddition
    ) {
        movieService.addMovieRank(movieRankAddition, id);
    }

}
