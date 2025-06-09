package bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordMismatchException extends RuntimeException{
    public PasswordMismatchException(){
        super("Password doesn't match with password confirmation");
    }
}
