package com.example.userservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionResponseDto {
    private Integer userId;
    private Integer amount;
    private TransactionStatusEnum status;
}
