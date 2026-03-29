package com.vatsaladhiya.routeq.routeq.dtos;

import com.vatsaladhiya.routeq.routeq.enums.TransactionMethod;
import com.vatsaladhiya.routeq.routeq.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransactionDto {
    private Long id;
    private Double amount;
    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RiderRideDto ride;

    private String transactionId;

    private LocalDateTime transactionTime;

    private WalletDto wallet;
}
