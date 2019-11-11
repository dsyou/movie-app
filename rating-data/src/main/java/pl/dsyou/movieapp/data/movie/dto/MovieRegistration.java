package pl.dsyou.movieapp.data.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRegistration {
    @NotBlank
    private String title;
    @NotBlank
    private String genre;
    @NotBlank
    private String productionDate;
}
