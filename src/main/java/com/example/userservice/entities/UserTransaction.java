package com.example.userservice.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@ToString
public class UserTransaction {
    @Id
    private Integer transactionId;
    private Integer userId;
    private Integer amount;
    private LocalDateTime transactionDate;
}
