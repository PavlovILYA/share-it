package ru.practicum.shareit.booking.exception;

public class BookingValidationException extends RuntimeException {
    public BookingValidationException() {
        super();
    }

    public BookingValidationException(String message) {
        super(message);
    }
}
