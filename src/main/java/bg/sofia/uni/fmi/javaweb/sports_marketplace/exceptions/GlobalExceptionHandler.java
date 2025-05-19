package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.channels.ReadPendingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExists(EmailAlreadyExistsException ex, WebRequest request){
        Map<String, Object> body= new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Profile already exists. Try logging in.");
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WrongEmailOrPasswordException.class)
    public ResponseEntity<Object> handleEmailAlreadyExists(WrongEmailOrPasswordException ex, WebRequest request){
        Map<String, Object> body= new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserDoesntExistException.class)
    public ResponseEntity<Object> handleUserDoesntExist(UserDoesntExistException ex, WebRequest request){
        Map<String, Object> body= new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<Object> handleUnAuthorizedAccess(UnAuthorizedAccessException ex, WebRequest request){
        Map<String, Object> body= new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }



}
