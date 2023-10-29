package saboroso.saborosoburguer.exceptions;

import org.hibernate.sql.ast.tree.from.UnknownTableReferenceException;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ExceptionHandler
public class HandlerForExceptions {

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
    public HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException () { return null; }
    public UnknownTableReferenceException unknownTableReferenceException() { return null; }
    public ConverterNotFoundException converterNotFoundException() { return null; }
    public CommandAcceptanceException commandAcceptanceException() {return null;}
}
