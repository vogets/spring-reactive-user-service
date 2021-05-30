package com.example.userservice.service;


import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.utilities.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Flux<UserDto> getAllUsers()
    {
        return userRepository.findAll().map(EntityDtoUtil::toUserDto);
    }
    public Mono<UserDto> getUserById(final Integer id)
    {
      return userRepository.findById(id)  .map(EntityDtoUtil::toUserDto);
    }
    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono)
    {
        return userDtoMono.map(EntityDtoUtil::toUserEntity)
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toUserDto);
    }

    public Mono<UserDto> updateUser(int id,Mono<UserDto> userDtoMono)
    {
        return userRepository.findById(id)
                .flatMap(u->userDtoMono.map(EntityDtoUtil::toUserEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toUserDto);

    }

    public Mono<Void> deleteUser(int id)
    {
        return userRepository.deleteById(id);

    }
}
