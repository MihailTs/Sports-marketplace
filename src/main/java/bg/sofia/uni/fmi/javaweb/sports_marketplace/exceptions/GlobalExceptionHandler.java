package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.ElementCollection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.nio.channels.ReadPendingException;
import java.sql.Time;
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

    @ExceptionHandler(NoSuchEventException.class)
    public ResponseEntity<Object> handleNoSuchEvent(NoSuchEventException ex, WebRequest request){
        Map<String, Object> body= new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TimeframeMismatchException.class)
    public ResponseEntity<Object> handleTimeFrameMismatch(TimeframeMismatchException ex, WebRequest request){
        Map<String, Object> body= new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<Object> handlePasswordMismatch(PasswordMismatchException ex, WebRequest request){
        Map<String, Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NoSuchForumException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchForum(NoSuchForumException ex) {
        Map<String, Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchForumPostException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchForumPost(NoSuchForumPostException ex) {
        Map<String, Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchForumCommentException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchForumComment(NoSuchForumCommentException ex) {
        Map<String, Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Map<String, Object>> handleJWTException(JwtException ex) {
        Map<String, Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

}
