package pl.dsyou.movierating.movie.application.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dsyou.movierating.common.handler.CmdHandler;
import pl.dsyou.movierating.movie.domain.MovieRepository;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class DeleteHandler implements CmdHandler<DeleteCmd> {

    private final MovieRepository repository;

    @Override
    public void handle(DeleteCmd cmd) {
        log.info("Deleting movie with uuid:{}", cmd.getMovieId());
        // co jak nie ma uuid ?
        repository.deleteBy(cmd.getMovieId());
    }
}
