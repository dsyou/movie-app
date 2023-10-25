package pl.dsyou.movierating.movie.application.command;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class EditCmd {
    String uuid;

    @NotBlank
    String title;
    @NotBlank
    String genre;
    @NotBlank
    String productionDate;
}
