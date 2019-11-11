package pl.dsyou.movieapp.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.dsyou.movieapp.data.movie.MovieService;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MoviesApiController {

    private final MovieService movieService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDetails> getMoviesTitles() {
        return movieService.getMovies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createMovie(@Valid MovieRegistration movieRegistration) {
        movieService.createMovie(movieRegistration);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void editMovie(@Valid MovieUpdate movieUpdate) {
        movieService.editMovie(movieUpdate);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable("id") long id) {
        movieService.deleteMovie(id);
    }

    @PostMapping("{id}/ranks")
    @ResponseStatus(HttpStatus.OK)
    public void addMovieRank(
            @PathVariable long id, @RequestBody @Valid MovieRankAddition movieRankAddition
    ) {
        movieService.addMovieRank(id, movieRankAddition);
    }

}
