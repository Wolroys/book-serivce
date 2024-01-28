package org.sber.resourcesservice.service;

import lombok.RequiredArgsConstructor;
import org.sber.bookingentity.entity.ReservationPeriod;
import org.sber.bookingentity.entity.Resource;
import org.sber.resourcesservice.repository.ReservationPeriodRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationPeriodRepository repository;

    public boolean isResourceAvailable(Resource resource, ZonedDateTime startTime, ZonedDateTime endTime){
        List<ReservationPeriod> overlappingReservations = repository
                .findOverlappingReservations(resource, startTime, endTime);

        return overlappingReservations.isEmpty();
    }
}
