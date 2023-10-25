package pl.dsyou.movierating.movie.view;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
class MovieContent {
    @Id
    private long id;
    private String uuid;

    private String title;
    private String genre;

    private Date productionDate;


    //    @SuppressWarnings("unused")
//    public Date productionDateFromString(String productionDate) {
//        return Try.of(() -> new SimpleDateFormat("dd-MM-yyyy").parse(productionDate))
//                .getOrElseThrow(() -> new MovieDateParseException(productionDate));
//    }
//
//    @SuppressWarnings("unused")
//    public String productionDateFromDate(Date productionDate) {
//        return new SimpleDateFormat("dd-MM-yyyy").format(productionDate);
//    }
}
