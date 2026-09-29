package com.trustdesk.exception;


import org.springframework.http.HttpStatus;

public class TicketNotFoundException extends BusinessException {
    public TicketNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public TicketNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Ticket not found");
    }
}
