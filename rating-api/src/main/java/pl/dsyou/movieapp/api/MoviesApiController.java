package pl.dsyou.movieapp.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.dsyou.movieapp.data.movie.MovieService;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRankAddition;

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

    @PostMapping("{id}/ranks")
    @ResponseStatus(HttpStatus.OK)
    public void addMovieRank(
            @PathVariable long id, @RequestBody @Valid MovieRankAddition movieRankAddition
    ) {
        movieService.addMovieRank(id, movieRankAddition);
    }

}
