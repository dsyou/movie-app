package pl.dsyou.movierating.rating.application.command;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class RateCreationCmd {
    @NotNull
    String movieId;
}
