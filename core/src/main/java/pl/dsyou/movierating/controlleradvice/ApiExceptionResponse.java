package pl.dsyou.movierating.controlleradvice;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiExceptionResponse {
    String url;
    String message;
}
