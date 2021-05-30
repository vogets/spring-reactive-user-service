package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entities.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public Flux<UserDto> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> getuserById(@PathVariable Integer id)
    {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<UserDto>> saveUser(@RequestBody Mono<UserDto> userDtoMono)
    {
        return userService.createUser(userDtoMono).map(ResponseEntity::ok);

    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable Integer id,@RequestBody Mono<UserDto> userDtoMono)
    {
        return userService.updateUser(id,userDtoMono).map(ResponseEntity::ok);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteUser(@PathVariable Integer id)
    {
        return userService.deleteUser(id);
    }



}
