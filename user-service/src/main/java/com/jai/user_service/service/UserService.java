package com.jai.user_service.service;

import com.jai.user_service.dto.UserDto;
import com.jai.user_service.entity.User;
import com.jai.user_service.exception.UserNotFoundException;
import com.jai.user_service.mapper.UserMapper;
import com.jai.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    final private UserRepository repo;
    final private UserMapper mapper;

    public UserService(UserRepository repo,UserMapper mapper){
        this.repo=repo;
        this.mapper=mapper;
    }

    public UserDto createUser(UserDto user){

        final User createdUser=mapper.toEntity(user);
        final User saved=repo.save(createdUser);
        return mapper.toDto(saved);
    }

    public UserDto getUserById(Long id){

        User reqUser=repo.findById(id)
                .orElse(null);
        if(reqUser==null) return null;
        return mapper.toDto(reqUser);
    }

    public void updateUser(Long id,UserDto dto){

        User user=repo.findById(id)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setAddress(dto.getAddress());
        user.setAlerting(dto.isAlerting());
        user.setEnergyAlertingThreshold(dto.getEnergyAlertingThreshold());
        repo.save(user);
    }

    public void deleteUser(Long id){

        User user=repo.findById(id)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        repo.delete(user);
    }
}
