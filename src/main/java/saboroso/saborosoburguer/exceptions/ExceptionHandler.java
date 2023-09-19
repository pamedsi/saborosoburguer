package saboroso.saborosoburguer.exceptions;

import org.springframework.context.ApplicationContextException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;

public class ExceptionHandler {

    public IllegalArgumentException illegalArgumentExceptionHandler () {
        return null;
    }
    public BadCredentialsException badCredentialsExceptionHandler () { return null; }
    public RuntimeException runtimeExceptionHandler() { return null; }
    public ApplicationContextException applicationContextExceptionHandler() { return null; }
    public NullPointerException nullPointerExceptionHandler() { return null; }
    public IndexOutOfBoundsException indexOutOfBoundsException() { return null; }
    public DataAccessResourceFailureException dataAccessResourceFailureException() { return null; }
    public DataIntegrityViolationException dataIntegrityViolationException() {return null; }
    public UnsupportedOperationException unsupportedOperationException() { return null; }
}
