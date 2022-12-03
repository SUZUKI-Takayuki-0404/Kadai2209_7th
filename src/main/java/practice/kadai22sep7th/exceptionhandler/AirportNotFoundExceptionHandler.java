package practice.kadai22sep7th.exceptionhandler;

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
public class AirportNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AirportNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoAirportFound(@NotNull AirportNotFoundException e, @NotNull HttpServletRequest request) {

        Map<String, String> body = new HashMap<>();
        body.put("timestamp", ZonedDateTime.now().toString());
        body.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("message", e.getMessage());
        body.put("path", request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
