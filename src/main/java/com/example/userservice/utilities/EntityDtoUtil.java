package com.example.userservice.utilities;

import com.example.userservice.dto.TransactionRequestDto;
import com.example.userservice.dto.TransactionResponseDto;
import com.example.userservice.dto.TransactionStatusEnum;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entities.User;
import com.example.userservice.entities.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static UserDto toUserDto(User user)
    {
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    public static User toUserEntity(UserDto userDto)
    {
        User entity=new User();
        BeanUtils.copyProperties(userDto,entity);
        return entity;
    }

    public static UserTransaction toUserTransactionEntity(TransactionRequestDto transactionRequestDto)
    {
        UserTransaction entity=new UserTransaction();
        entity.setUserId(transactionRequestDto.getUserId());
        entity.setAmount(transactionRequestDto.getAmount());
        entity.setTransactionDate(LocalDateTime.now());
        return entity;
    }

    public static TransactionResponseDto toTransactionResponseDto(TransactionRequestDto userTransaction, TransactionStatusEnum status)
    {
        TransactionResponseDto dto=new TransactionResponseDto();
        dto.setUserId(userTransaction.getUserId());
        dto.setAmount(userTransaction.getAmount());
        dto.setStatus(status);
        return dto;
    }
}
