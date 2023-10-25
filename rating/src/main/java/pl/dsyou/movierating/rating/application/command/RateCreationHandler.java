package pl.dsyou.movierating.rating.application.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dsyou.movierating.common.handler.CmdHandler;
import pl.dsyou.movierating.movie.domain.MovieRepository;
import pl.dsyou.movierating.rating.domain.Rate;
import pl.dsyou.movierating.rating.domain.RateRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RateCreationHandler implements CmdHandler<RateCreationCmd> {
    private final RateRepository ratingRepository;
    private final MovieRepository movieRepository;

    @Override
    public void handle(RateCreationCmd cmd) {
        final String movieUuid = cmd.getMovieId();
        // Return Failure Succes ?
        long movieId = movieRepository.findBy(movieUuid).get().getId();

        log.info("Created movie rank with correlation movie id: {}", movieId);

        Rate rate = new Rate(movieId);

        ratingRepository.save(rate);
    }

    private float countAverageOfMovieRanks(List<Float> ranks) {
        float sum = 0;
        for (Float rank : ranks) {
            sum += rank;
        }
        return sum / (ranks.size() - 1);
    }

    private float round(float sumOfRanks, int decimalPlace) {
        return BigDecimal.valueOf(sumOfRanks).setScale(decimalPlace, RoundingMode.UP).floatValue();
    }

    private void foo() {
//       Movie movie = movieRepository.findById(movieId)
//               .orElseThrow(() -> new MovieNotFoundException(movieId));
//
//       MovieRating movieRating = movie.getMovieRating();
//
//       List<Float> ranks = movieRating.getRanks();
//       ranks.add(movieRankAddition.getRank());
//
//       float sumOfMovieRanks;
//       if (ranks.get(0) == 0f && ranks.size() == 2) {
//           sumOfMovieRanks = ranks.get(1);
//       } else {
//           sumOfMovieRanks = countAverageOfMovieRanks(ranks);
//       }
//       log.debug("Calculated arithmetic average:{} , for movie with id:{}", sumOfMovieRanks, movieId);
//       float roundedSumOfMovieRanks = round(sumOfMovieRanks, 2);
//
//       movieRating. (roundedSumOfMovieRanks);
    }

}
