package app.prog.evv.drillang.exception.auth;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String message) {
        super(message);
    }

}
