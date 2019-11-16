package pl.dsyou.movieapp.exception.interceptor;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.dsyou.movieapp.data.movie.exception.MovieDateParseException;
import pl.dsyou.movieapp.data.movie.exception.MovieNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    ApiExceptionResponse entityNotFoundException(@NonNull HttpServletRequest request,
                                                 @NonNull MovieNotFoundException ex) {

        return ApiExceptionResponse.builder()
                .url(request.getRequestURI())
                .message(ex.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler(MovieDateParseException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    ApiExceptionResponse dateCannotBeParseException(@NonNull HttpServletRequest request,
                                                    @NonNull MovieNotFoundException ex) {

        return ApiExceptionResponse.builder()
                .url(request.getRequestURI())
                .message(ex.getLocalizedMessage())
                .build();
    }
}
