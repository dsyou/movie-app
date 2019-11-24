package pl.dsyou.movieapp.data.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dsyou.movieapp.core.dto.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRankAddition {
    @Min(1)
    @Max(10)
    private float rank;
}
