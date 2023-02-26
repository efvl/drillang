package app.prog.evv.drillang.exception.auth;

public class TokenValidationException extends RuntimeException {

    public TokenValidationException(String message) {
        super(message);
    }

}
