package pl.dsyou.movierating.movie.domain;

import lombok.Value;

import java.util.Date;

@Value
// TODO record ?
class Description {
    String title;
    String genre;
    Date productionDate;
}
