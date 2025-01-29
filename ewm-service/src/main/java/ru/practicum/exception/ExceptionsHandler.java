package ru.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.practicum.dto.ApiError;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));
        String stackTrace = out.toString();
        return new ApiError(Collections.singletonList(stackTrace),
                "Ошибка валидации параметра",
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now().toString()
        );
    }
}
