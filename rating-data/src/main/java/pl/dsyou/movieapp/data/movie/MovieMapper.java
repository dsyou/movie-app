package pl.dsyou.movieapp.data.movie;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import pl.dsyou.movieapp.core.mapper.CentralConfigMapper;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.Movie;

@Mapper(
        config = CentralConfigMapper.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "productionDate", source = "productionDate")
    @Mapping(target = "rank", source = "rank", defaultValue = "0")
    MovieDetails toMovieDetails(Movie movie);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "productionDate", source = "productionDate")
    @Mapping(target = "rank", ignore = true)
    Movie toMovie(MovieRegistration movieRegistration);


    Movie toMovie(MovieUpdate movieUpdate);

}

