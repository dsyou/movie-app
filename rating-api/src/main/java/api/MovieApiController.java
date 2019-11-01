package api;

import data.movie.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieApiController {

    private final MovieService movieService;

    public MovieApiController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public void getMoviesTitles() {
        // get All movies
    }

    @GetMapping
    public void getMoviesRanks() {
        // get All movies ranks
    }

    @PostMapping
    public void addMovieRank() {
        // add movie to rank
    }

}
