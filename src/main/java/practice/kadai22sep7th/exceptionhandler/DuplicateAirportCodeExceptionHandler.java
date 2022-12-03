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
import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class DuplicateAirportCodeExceptionHandler extends ResponseEntityExceptionHandler {

    private final static Logger logger = Logger.getLogger(DuplicateAirportCodeExceptionHandler.class.getName());

    @ExceptionHandler(value = DuplicateAirportCodeException.class)
    public ResponseEntity<Map<String, String>> handleAirportCodeDuplication(@NotNull DuplicateAirportCodeException e, @NotNull HttpServletRequest request) {

        logger.log(Level.INFO, "Exception was thrown due to AirportCode Duplication but properly handled", e);

        Map<String, String> body = new HashMap<>();
        body.put("timestamp", ZonedDateTime.now().toString());
        body.put("status", String.valueOf(HttpStatus.CONFLICT.value()));
        body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
        body.put("message", e.getMessage());
        body.put("path", request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
