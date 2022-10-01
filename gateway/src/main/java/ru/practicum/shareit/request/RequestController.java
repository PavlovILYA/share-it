package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestCreateDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Slf4j
@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestClient requestClient;

    private static final String USER_ID_HEADER = "X-Sharer-User-Id";
    private static final String FROM_DEFAULT = "0";
    private static final String SIZE_DEFAULT = "5";

    @PostMapping
    public ResponseEntity<Object> saveRequest(@RequestHeader(USER_ID_HEADER) Long userId,
                                              @Valid @RequestBody ItemRequestCreateDto itemRequestCreateDto) {
        log.debug("Creating itemRequest: userId={}, body: {}", userId, itemRequestCreateDto);
        return requestClient.saveRequest(userId, itemRequestCreateDto);
    }

    @GetMapping
    public ResponseEntity<Object> getMyRequests(@RequestHeader(USER_ID_HEADER) Long userId) {
        log.debug("Get requests that user {} made", userId);
        return requestClient.getMyRequests(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAlienRequests(@RequestHeader(USER_ID_HEADER) Long userId,
                                                   @PositiveOrZero
                                                   @RequestParam(name = "from", defaultValue = FROM_DEFAULT)
                                                   int from,
                                                   @Positive
                                                   @RequestParam(name = "size", defaultValue = SIZE_DEFAULT)
                                                   int size) {
        log.debug("Get requests that others made: userId={}, from={}, size={}", userId, from, size);
        return requestClient.getAlienRequests(userId, from, size);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getRequestById(@RequestHeader(USER_ID_HEADER) Long userId,
                                                 @PathVariable("requestId") Long requestId) {
        log.debug("Get request {}: userId={}", requestId, userId);
        return requestClient.getRequestById(userId, requestId);
    }
}