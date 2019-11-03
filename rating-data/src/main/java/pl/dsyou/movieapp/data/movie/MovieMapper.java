package pl.dsyou.movieapp.data.movie;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;


import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.mongo.model.Movie;

import pl.dsyou.movieapp.core.mapper.CentralConfigMapper;

@Mapper(
        config = CentralConfigMapper.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "productionDate", source = "productionDate")
    @Mapping(target = "score", source = "rank")
    MovieDetails toMovieDetails(Movie movie);

}

