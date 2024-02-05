package org.sber.resourcesservice.controller;

import lombok.RequiredArgsConstructor;
import org.sber.resourcesservice.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/acquire")
    public ResponseEntity<Long> acquireResource(@RequestBody Long userId, @RequestBody Long resourceId, @RequestBody LocalDateTime startTime,
                                                @RequestBody LocalDateTime endTime){

        Long reservationId = reservationService.acquire(userId, resourceId, startTime, endTime);

        return new ResponseEntity<>(reservationId, HttpStatus.OK);
    }
}
