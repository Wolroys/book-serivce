package org.sber.resourcesservice.repository;

import org.sber.bookingentity.entity.ReservationPeriod;
import org.sber.bookingentity.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface ReservationPeriodRepository extends JpaRepository<ReservationPeriod, Long> {

    @Query("SELECT r FROM ReservationPeriod r " +
            "WHERE r.resource = :resource " +
            "AND r.startTime <= :endTime " +
            "AND r.endTime >= :startTime")
    List<ReservationPeriod> findOverlappingReservations(Resource resource, ZonedDateTime startTime,
                                                        ZonedDateTime endTime);
}
