package task.simpleShop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleExistsElementException(
            final ExistsElementException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(Map.of("error", e.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleNotFoundException(
            final NotFoundException e) {
        log.info(e.getMessage(), e);
        return new ResponseEntity<>(Map.of("error",
                e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
