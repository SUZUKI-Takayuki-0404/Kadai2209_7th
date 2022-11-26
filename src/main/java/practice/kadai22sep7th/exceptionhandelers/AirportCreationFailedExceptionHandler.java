package practice.kadai22sep7th.exceptionhandelers;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AirportCreationFailedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AirportCreationFailedException.class)
    public ResponseEntity<Map<String, String>> handleAirportCreationFailure(@NotNull AirportCreationFailedException e, @NotNull HttpServletRequest request) {

        Map<String, String> body = new HashMap<>();
        body.put("timestamp", ZonedDateTime.now().toString());
        body.put("status", String.valueOf(HttpStatus.CONFLICT.value()));
        body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
        body.put("message", e.getMessage());
        body.put("path", request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
