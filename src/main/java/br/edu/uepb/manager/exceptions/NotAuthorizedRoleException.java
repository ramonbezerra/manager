package br.edu.uepb.manager.exceptions;

public class NotAuthorizedRoleException extends Exception {
    public NotAuthorizedRoleException(String message) {
        super(message);
    }
}
