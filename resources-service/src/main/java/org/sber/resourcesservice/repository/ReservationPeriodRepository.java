package org.sber.resourcesservice.repository;

import org.sber.bookingentity.entity.ReservationPeriod;
import org.sber.bookingentity.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationPeriodRepository extends JpaRepository<ReservationPeriod, Long> {

    @Query("SELECT r FROM ReservationPeriod r " +
            "WHERE r.resource = :resource " +
            "AND r.startTime <= :endTime " +
            "AND r.endTime >= :startTime")
    Optional<ReservationPeriod> findOverlappingReservations(Resource resource, ZonedDateTime startTime,
                                                            ZonedDateTime endTime);

    List<ReservationPeriod> findAllByUserId(Long userId);

    List<ReservationPeriod> findAllByResource(Resource resource);

    @Query("SELECT r FROM ReservationPeriod r " +
            "WHERE r.resource = :resource " +
            "AND r.endTime > :startTime " +
            "ORDER BY r.startTime ASC")
    Optional<ReservationPeriod> findNextAvailableReservation(Resource resource, ZonedDateTime startTime);
}
