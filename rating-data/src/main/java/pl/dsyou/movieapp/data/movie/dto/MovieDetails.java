package pl.dsyou.movieapp.data.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dsyou.movieapp.core.dto.DTO;

@DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails {
    private String id;
    private String title;
    private String genre;
    private String productionDate;
    private float rank;
}
