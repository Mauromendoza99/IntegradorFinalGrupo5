package com.ar.cac.IntegradorFinalGrupo5.mappers;

import com.ar.cac.IntegradorFinalGrupo5.entities.User;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.UserDto;

import java.time.LocalDateTime;

public class UserMapper {

    public static User dtoTouser(UserDto dto){
        User userMap = new User();
        userMap.setUsername(dto.getUsername());
        userMap.setPassword(dto.getPassword());
        userMap.setAddress(dto.getAddress());
        userMap.setDni(dto.getDni());
        userMap.setBirthday_date(dto.getBirthday_date());
        userMap.setEmail(dto.getEmail());
        userMap.setCreated_at(LocalDateTime.now());
        userMap.setUpdated_at(LocalDateTime.now());
        return userMap;
    }

    public static UserDto userToDto(User user){
        UserDto dtoMap = new UserDto();
        dtoMap.setUsername(user.getUsername());
        dtoMap.setPassword(user.getPassword());
        dtoMap.setId(user.getId());
        dtoMap.setAddress(user.getAddress());
        dtoMap.setEmail(user.getEmail());
        dtoMap.setBirthday_date(user.getBirthday_date());
        dtoMap.setDni(user.getDni());
        dtoMap.setCreated_at(user.getCreated_at());
        dtoMap.setUpdated_at(user.getUpdated_at());
        return dtoMap;
    }


}
