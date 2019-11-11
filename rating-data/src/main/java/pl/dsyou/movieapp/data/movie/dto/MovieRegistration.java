package pl.dsyou.movieapp.data.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dsyou.movieapp.core.dto.DTO;

import javax.validation.constraints.NotBlank;

@DTO
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
