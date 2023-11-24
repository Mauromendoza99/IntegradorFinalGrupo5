package com.ar.cac.IntegradorFinalGrupo5.services;

import com.ar.cac.IntegradorFinalGrupo5.entities.User;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.UserDto;
import com.ar.cac.IntegradorFinalGrupo5.mappers.UserMapper;
import com.ar.cac.IntegradorFinalGrupo5.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    // Constructor Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Methods
    public List<UserDto> getUsers(){

        return userRepository.findAll().stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());

    }

    public UserDto getUserById(Long id){
        User user = userRepository.findById(id).get();
        user.setPassword("******");
        return UserMapper.userToDto(user);
    }

    public UserDto createUser(UserDto userDto){
        // TODO: agregar validacion de email existente
        User entity = UserMapper.dtoTouser(userDto);
        User entitySaved = userRepository.save(entity);
        userDto = UserMapper.userToDto(entitySaved);
        //user.setPassword("******");
        return userDto;
    }

    public String deleteUser(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "El usuario con id: " + id + " ha sido eliminado";
        } else {
            return "El usuario con id: " + id + ", no ha sido eliminado";
        }
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        if (userRepository.existsById(id)){
            User userToModify = userRepository.findById(id).get();
            // Validar qué datos no vienen en null para setearlos al objeto ya creado

            // Logica del Patch
            if (userDto.getUsername() != null){
                userToModify.setUsername(userDto.getUsername());
            }

            // TODO: agregar validacion de email existente
            if (userDto.getEmail() != null){
                userToModify.setEmail(userDto.getEmail());
            }

            if (userDto.getPassword() != null){
                userToModify.setPassword(userDto.getPassword());
            }

            if (userDto.getDni() != null){
                userToModify.setDni(userDto.getDni());
            }

            if (userDto.getAddress() != null){
                userToModify.setAddress(userDto.getAddress());
            }
            if (userDto.getBirthday_date() != null){
                userToModify.setBirthday_date(userDto.getBirthday_date());
            }

            userToModify.setUpdated_at(LocalDateTime.now());

            User userModified = userRepository.save(userToModify);

            return UserMapper.userToDto(userModified);
        }

        return null;
    }

    // Validar que existan usuarios unicos por mail
    /*
    public User validateUserByEmail(UserDto dto){
        return repository.findByEmail(dto.getEmail());
    }
    */
}
