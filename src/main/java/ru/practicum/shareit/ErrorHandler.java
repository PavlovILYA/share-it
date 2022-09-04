package ru.practicum.shareit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.booking.exception.BookingValidationException;
import ru.practicum.shareit.booking.exception.BookingNotFoundException;
import ru.practicum.shareit.booking.exception.UnavailableItemException;
import ru.practicum.shareit.item.exception.InvalidOwnerException;
import ru.practicum.shareit.item.exception.ItemNotFoundException;
import ru.practicum.shareit.user.exception.EmailAlreadyExistsException;
import ru.practicum.shareit.user.exception.UserNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final MethodArgumentNotValidException e) {
        log.info("400 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 400, e.getMessage());
    }

    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCustomValidationException(final CustomValidationException e) {
        log.info("400 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 400, e.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEmailExistException(final EmailAlreadyExistsException e) {
        log.info("409 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 409, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(final UserNotFoundException e) {
        log.info("404 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 404, e.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleItemNotFoundException(final ItemNotFoundException e) {
        log.info("404 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 404, e.getMessage());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingRequestHeaderException(final MissingRequestHeaderException e) {
        log.info("400 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 400, e.getMessage());
    }

    @ExceptionHandler(InvalidOwnerException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleWrongItemOwnerException(final InvalidOwnerException e) {
        log.info("403 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 403, e.getMessage());
    }

    @ExceptionHandler(UnavailableItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUnavailableItemException(final UnavailableItemException e) {
        log.info("400 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 400, e.getMessage());
    }

    @ExceptionHandler(BookingValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBookingValidationException(final BookingValidationException e) {
        log.info("400 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 400, e.getMessage());
    }

    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookingNotFoundException(final BookingNotFoundException e) {
        log.info("404 {}", e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), 404, e.getMessage());
    }
}
