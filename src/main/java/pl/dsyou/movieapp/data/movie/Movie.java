package pl.dsyou.movieapp.data.movie;

import lombok.Data;

@Data
public class Movie {

    private String title;
    private String genre;

    private String productionDate; // () -> date 

    private float rank = 3.1f; // from 0 to 10
}
