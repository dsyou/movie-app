package pl.dsyou.movierating.controlleradvice;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.dsyou.movierating.common.exception.ElementNotFoundException;
import pl.dsyou.movierating.common.exception.ParseException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    ApiExceptionResponse entityNotFoundException(@NonNull HttpServletRequest request,
                                                 @NonNull ElementNotFoundException ex) {

        return ApiExceptionResponse.builder()
                .url(request.getRequestURI())
                .message(ex.getLocalizedMessage())
                .build();
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    ApiExceptionResponse dateCannotBeParseException(@NonNull HttpServletRequest request,
                                                    @NonNull ParseException ex) {

        return ApiExceptionResponse.builder()
                .url(request.getRequestURI())
                .message(ex.getLocalizedMessage())
                .build();
    }
}
