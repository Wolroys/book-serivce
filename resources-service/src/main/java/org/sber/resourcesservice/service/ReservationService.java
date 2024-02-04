package org.sber.resourcesservice.service;

import lombok.RequiredArgsConstructor;
import org.sber.bookingentity.entity.ReservationPeriod;
import org.sber.bookingentity.entity.Resource;
import org.sber.resourcesservice.ReservationException;
import org.sber.resourcesservice.repository.ReservationPeriodRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationPeriodRepository repository;

    public Long Acquire(Long userId, Resource resource, ZonedDateTime startTime, ZonedDateTime endTime){
        Optional<ReservationPeriod> existedReservation = repository.findOverlappingReservations(resource, startTime, endTime);\

        if (existedReservation.isPresent())
            throw new ReservationException("This resource is already reserved during this time period");

        ReservationPeriod reservation = new ReservationPeriod();

        reservation.setUserId(userId);
        reservation.setResource(resource);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);

        return repository.saveAndFlush(reservation).getId();
    }

    public boolean release(Long id){
        Optional<ReservationPeriod> reservationPeriod = repository.findById(id);

        if (reservationPeriod.isPresent()){
            repository.delete(reservationPeriod.get());
            return true;
        }

        return false;
    }

    public List<ReservationPeriod> findReservationsByUserId(Long userId){
        return repository.findAllByUserId(userId);
    }

    public Optional<ReservationPeriod> findReservationById(Long id){
        return repository.findById(id);
    }

    public List<ReservationPeriod> findReservationsByResource(Resource resource){
        return repository.findAllByResource(resource);
    }

    public Optional<ReservationPeriod> findNextAvailableReservation(Resource resource, ZonedDateTime startTime){ //ZonedDateTime
        return repository.findNextAvailableReservation(resource, startTime);
    }
}
