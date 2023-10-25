package pl.dsyou.movierating.movie.application.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dsyou.movierating.common.handler.CmdHandler;
import pl.dsyou.movierating.movie.domain.Movie;
import pl.dsyou.movierating.movie.domain.MovieRepository;
import pl.dsyou.movierating.movie.domain.exception.MovieNotFoundException;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class EditHandler implements CmdHandler<EditCmd> {
    private final MovieRepository repository;

    @Override
    public void handle(EditCmd command) {
        log.info("Editing movie with id:{}", command.getUuid());
        Movie movie = repository.findBy(command.getUuid()).
                orElseThrow(() -> new MovieNotFoundException(command.getUuid()));

        repository.save(movie);
    }
}
