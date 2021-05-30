package com.example.userservice.service;


import com.example.userservice.dto.TransactionRequestDto;
import com.example.userservice.dto.TransactionResponseDto;
import com.example.userservice.dto.TransactionStatusEnum;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.repository.UserTransactionRepository;
import com.example.userservice.utilities.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto transactionRequestDto)
    {
        return userRepository.updateUserBalance(transactionRequestDto.getUserId(),transactionRequestDto.getAmount())
                      .filter(Boolean::booleanValue)
                      .map(b-> EntityDtoUtil.toUserTransactionEntity(transactionRequestDto))
        .flatMap(userTransactionRepository::save)
        .map(ut->EntityDtoUtil.toTransactionResponseDto(transactionRequestDto, TransactionStatusEnum.APPROVED))
        .defaultIfEmpty(EntityDtoUtil.toTransactionResponseDto(transactionRequestDto,TransactionStatusEnum.DECLINED));
    }
}
