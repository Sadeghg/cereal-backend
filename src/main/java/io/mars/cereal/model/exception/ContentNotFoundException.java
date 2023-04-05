package io.mars.cereal.model.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ContentNotFoundException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

}
