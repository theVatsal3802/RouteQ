package com.vatsaladhiya.routeq.routeq.entities;

import com.vatsaladhiya.routeq.routeq.enums.TransactionMethod;
import com.vatsaladhiya.routeq.routeq.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WalletTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private RideEntity ride;

    private String transactionId;

    @CreationTimestamp
    private LocalDateTime transactionTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private WalletEntity wallet;
}
