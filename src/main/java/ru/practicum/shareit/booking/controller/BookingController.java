package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingCreateDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.booking.util.BookingState;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> create(@RequestHeader("X-Sharer-User-Id") Long userId,
                                             @Valid @RequestBody BookingCreateDto bookingCreateDto) {
        log.info("Create booking {}, userid {}", bookingCreateDto, userId);
        return ResponseEntity.ok(bookingService.create(userId, bookingCreateDto));
    }

    @PatchMapping("{bookingId}")
    public ResponseEntity<BookingDto> approveBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                     @PathVariable Long bookingId,
                                                     @RequestParam boolean approved) {
        log.info("User {} change approve {} booking {}", userId, approved, bookingId);
        return ResponseEntity.ok(bookingService.approveBooking(userId, bookingId, approved));
    }

    @GetMapping("{bookingId}")
    public ResponseEntity<BookingDto> get(@RequestHeader("X-Sharer-User-Id") Long userId,
                                          @PathVariable Long bookingId) {
        log.info("Get booking {} user {}", bookingId, userId);
        return ResponseEntity.ok(bookingService.getById(userId, bookingId));
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAll(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                   @RequestParam(defaultValue = "all") String state) {
        log.info("Get All booking booker {}, state {}", userId, state);
        return ResponseEntity.ok(bookingService.getAll(userId, BookingState.fromStringIgnoreCase(state), false));
    }

    @GetMapping("/owner")
    public ResponseEntity<List<BookingDto>> getAllOwner(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                        @RequestParam(defaultValue = "all") String state) {
        log.info("Get All booking owner {}, state {}", userId, state);
        return ResponseEntity.ok(bookingService.getAll(userId, BookingState.fromStringIgnoreCase(state), true));
    }
}