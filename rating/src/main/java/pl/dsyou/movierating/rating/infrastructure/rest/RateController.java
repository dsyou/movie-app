package pl.dsyou.movierating.rating.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.dsyou.movierating.rating.application.command.RateCreationCmd;
import pl.dsyou.movierating.rating.application.command.RateCreationHandler;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
class RateController {
    private final RateCreationHandler creationHandler;

    @PostMapping
    @ResponseStatus(CREATED)
    void addRankToMovie(@RequestBody RateCreationCmd cmd) {
        this.creationHandler.handle(cmd);
    }

    // no content
}
