package pl.dsyou.movieapp.exception.interceptor;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiExceptionResponse {
    private String url;
    private String message;
}
