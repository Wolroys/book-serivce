package org.sber.resourcesservice.service;

import lombok.RequiredArgsConstructor;
import org.sber.bookingentity.entity.ReservationPeriod;
import org.sber.resourcesservice.exception.ReservationException;
import org.sber.resourcesservice.repository.ReservationPeriodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationPeriodRepository reservationRepository;

    @Transactional
    public Long acquire(Long userId, Long resourceId, LocalDateTime startTime, LocalDateTime endTime){
        Optional<ReservationPeriod> existedReservation = reservationRepository.
                findOverlappingReservations(resourceId, startTime, endTime);

        if (existedReservation.isPresent())
            throw new ReservationException("This resourceId is already reserved during this time period");

        ReservationPeriod reservation = new ReservationPeriod();

        reservation.setUserId(userId);
        reservation.setResourceId(resourceId);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);

        return reservationRepository.saveAndFlush(reservation).getId();
    }

    @Transactional
    public boolean release(Long id){
        Optional<ReservationPeriod> reservationPeriod = reservationRepository.findById(id);

        if (reservationPeriod.isPresent()){
            reservationRepository.delete(reservationPeriod.get());
            return true;
        }

        return false;
    }

    public List<ReservationPeriod> findReservationsByUserId(Long userId){
        return reservationRepository.findAllByUserId(userId);
    }

    public Optional<ReservationPeriod> findReservationById(Long id){
        return reservationRepository.findById(id);
    }

    public List<ReservationPeriod> findReservationsByResource(Long resourceId){
        return reservationRepository.findAllByResourceId(resourceId);
    }

    public Optional<ReservationPeriod> findNextAvailableReservation(Long resourceId, LocalDateTime startTime){
        return reservationRepository.findNextAvailableReservation(resourceId, startTime);
    }
}
