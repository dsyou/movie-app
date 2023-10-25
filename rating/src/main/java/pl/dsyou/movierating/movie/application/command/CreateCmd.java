package pl.dsyou.movierating.movie.application.command;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Value
public class CreateCmd {
    @NotBlank
    String title;
    @NotBlank
    String genre;
    @NotBlank
    Date productionDate;
}
