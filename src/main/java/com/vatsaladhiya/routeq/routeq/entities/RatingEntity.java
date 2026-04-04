package com.vatsaladhiya.routeq.routeq.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_rating_rider", columnList = "rider_id"),
        @Index(name = "idx_rating_driver", columnList = "driver_id")
})
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private RideEntity ride;

    @ManyToOne(fetch = FetchType.LAZY)
    private RiderEntity rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private DriverEntity driver;

    private Integer driverRating;

    private Integer riderRating;
}
