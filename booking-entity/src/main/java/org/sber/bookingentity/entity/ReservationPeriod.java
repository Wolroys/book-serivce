package org.sber.bookingentity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation_period")
public class ReservationPeriod{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long resourceId;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;
}
