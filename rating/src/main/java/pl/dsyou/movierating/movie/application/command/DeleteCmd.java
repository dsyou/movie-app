package pl.dsyou.movierating.movie.application.command;

import lombok.Value;

@Value
public class DeleteCmd {
    String movieId;
}
