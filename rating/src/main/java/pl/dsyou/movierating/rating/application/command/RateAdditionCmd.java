package pl.dsyou.movierating.rating.application.command;

import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
class RateAdditionCmd {
    @NotNull
    long movieId;

    @Min(1)
    @Max(10)
    float rank;
}
