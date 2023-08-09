package com.github.crudprac.service.advice;

import com.github.crudprac.exceptions.BadRequestException;
import com.github.crudprac.exceptions.NotFoundException;
import com.github.crudprac.dto.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse handleBadRequestException(BadRequestException badRequestException) {
        String exceptionMessage = badRequestException.getMessage();
        log.warn("Bad request exception: {}", exceptionMessage);
        return new MessageResponse(exceptionMessage);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponse handleNotFoundException(NotFoundException notFoundException) {
        String exceptionMessage = notFoundException.getMessage();
        log.warn("Not found exception: {}", exceptionMessage);
        return new MessageResponse(exceptionMessage);
    }
}
