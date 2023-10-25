package pl.dsyou.movierating.movie.application.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dsyou.movierating.common.handler.CmdHandler;
import pl.dsyou.movierating.movie.domain.Movie;
import pl.dsyou.movierating.movie.domain.MovieRepository;

@Service
@Transactional
@AllArgsConstructor
public class CreateHandler implements CmdHandler<CreateCmd> {

    private final MovieRepository repository;

    @Override
    public void handle(CreateCmd cmd) {
        Movie movie = Movie.of(cmd.getTitle(), cmd.getGenre(), cmd.getProductionDate());
        repository.save(movie);
        // event raise
    }
}
