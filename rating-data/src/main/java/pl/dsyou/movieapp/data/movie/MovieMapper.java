package pl.dsyou.movieapp.data.movie;

import io.vavr.control.Try;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import pl.dsyou.movieapp.core.mapper.CentralConfigMapper;
import pl.dsyou.movieapp.data.movie.dto.MovieDetails;
import pl.dsyou.movieapp.data.movie.dto.MovieRegistration;
import pl.dsyou.movieapp.data.movie.dto.MovieUpdate;
import pl.dsyou.movieapp.data.movie.exception.MovieDateParseException;
import pl.dsyou.movieapp.data.movie.mongo.model.movie.Movie;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(
        config = CentralConfigMapper.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class MovieMapper {

    public static MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "productionDate", source = "productionDate")
    @Mapping(target = "rank", source = "rank", defaultValue = "0")
    public abstract MovieDetails toMovieDetails(Movie movie);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "productionDate", source = "productionDate")
    @Mapping(target = "rank", ignore = true)
    public abstract Movie toMovie(MovieRegistration movieRegistration);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "genre", source = "genre")
    @Mapping(target = "productionDate", source = "productionDate")
    @Mapping(target = "rank", ignore = true)
    public abstract void toMovie(MovieUpdate movieUpdate, @MappingTarget Movie movie);

    @SuppressWarnings("unused")
    public Date productionDateFromString(String productionDate) {
        return Try.of(() -> new SimpleDateFormat("dd-MM-yyyy").parse(productionDate))
                .getOrElseThrow(() -> new MovieDateParseException(productionDate));
    }

    @SuppressWarnings("unused")
    public String productionDateFromDate(Date productionDate) {
        return new SimpleDateFormat("dd-MM-yyyy").format(productionDate);
    }

}

