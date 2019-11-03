package pl.dsyou.movieapp.api;


import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dsyou.movieapp.data.movie.MovieService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MovieApiController {

    private final MovieService movieService;

    @GetMapping
    public String getMoviesTitles() {
        return "Hello World !";
    }

//    @GetMapping
//    public void getMoviesRanks() {
//    }

    @PostMapping
    public void addMovieRank() {
    }

}
