package pl.dsyou.movierating.movie.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.dsyou.movierating.common.rest.version.ApiVersion;
import pl.dsyou.movierating.movie.application.command.*;
import pl.dsyou.movierating.rating.application.command.RateCreationCmd;
import pl.dsyou.movierating.rating.application.command.RateCreationHandler;

import javax.validation.Valid;

@ApiVersion(1)
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
class MoviesController {

    private final CreateHandler createHandler;
    private final EditHandler editHandler;
    private final DeleteHandler deleteHandler;

    private final RateCreationHandler additionHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createMovie(@RequestBody @Valid CreateCmd createCmd) {
        createHandler.handle(createCmd);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    void editMovie(@RequestBody @Valid EditCmd editCmd) {
        editHandler.handle(editCmd);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteMovie(@PathVariable("id") String id) {
        deleteHandler.handle(new DeleteCmd(id));
    }

    @PostMapping("{id}/ranks")
    @ResponseStatus(HttpStatus.OK)
    void addMovieRank(@RequestBody @Valid RateCreationCmd additionCmd) {
        additionHandler.handle(additionCmd);
    }

}
