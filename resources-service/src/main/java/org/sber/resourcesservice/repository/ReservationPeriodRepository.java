package org.sber.resourcesservice.repository;

import org.sber.bookingentity.entity.ReservationPeriod;
import org.sber.bookingentity.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationPeriodRepository extends JpaRepository<ReservationPeriod, Long> {

    @Query("SELECT r FROM ReservationPeriod r " +
            "WHERE r.resourceId = :resourceId " +
            "AND r.startTime <= :endTime " +
            "AND r.endTime >= :startTime")
    Optional<ReservationPeriod> findOverlappingReservations(Long resourceId, ZonedDateTime startTime,
                                                            ZonedDateTime endTime);

    List<ReservationPeriod> findAllByUserId(Long userId);

    List<ReservationPeriod> findAllByResourceId(Long resourceId);

    @Query("SELECT r FROM ReservationPeriod r " +
            "WHERE r.resourceId = :resourceId " +
            "AND r.endTime > :startTime " +
            "ORDER BY r.startTime ASC")
    Optional<ReservationPeriod> findNextAvailableReservation(Long resourceId, ZonedDateTime startTime);
}
