package com.ar.cac.IntegradorFinalGrupo5.services;

import com.ar.cac.IntegradorFinalGrupo5.entities.Account;
import com.ar.cac.IntegradorFinalGrupo5.entities.User;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.UserDto;
import com.ar.cac.IntegradorFinalGrupo5.entities.enums.AccountType;
import com.ar.cac.IntegradorFinalGrupo5.mappers.UserMapper;
import com.ar.cac.IntegradorFinalGrupo5.repositories.AccountRepository;
import com.ar.cac.IntegradorFinalGrupo5.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    // Constructor Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Methods


    public List<UserDto> getUsers() {
        //COMPLETAMOS OTRA LISTA SOLO CON LOS REGISTROS HABILITADOS PARA NO MOSTRAR LOS DESHABILITADO
        List<UserDto> users = userRepository.findAll().stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
        List<UserDto> usuariosFinales = new ArrayList<>();
        for (UserDto userDto : users) {
            if (UserMapper.dtoTouser(userDto).getEnabled() == true) {
                usuariosFinales.add(userDto);
            }
        }
        return usuariosFinales;
    }

    //    INDICAMOS QUE ES POSIBLE QUE SE ARROJE UNA EXCEPTION Y USAMOS UN DTO PARA ACCEDER AL METODO TOSTRING
    public UserDto getUserById(Long id) throws Exception {
        User user = userRepository.findById(id).get();
        if (user.getEnabled() == true) {
            return UserMapper.userToDto(user);
        } else {
            return null;
        }

    }


    public UserDto createUser(UserDto userDto) {
        //SETEAMOS QUE EL USUARIO SE CREE HABILITADO
        userDto.setEnabled(true);
        User user = UserMapper.dtoTouser(userDto);
        // VERIFICACION DE DATOS DUPLICADOS
        if (!existsEmail(user)) {
            User entitySaved = userRepository.save(user);
//            SECCION PARA LA CREACION AUTOMATICA DE UNA CUENTA AL CREAR UN USUARIO
//            String defaultCbu = (String)(Math.random()*9999999999999999999999+1);
//            Account defaultAccount = new Account();
//            defaultAccount.setType(AccountType.CAJA_AHORRO_PESOS);
//            defaultAccount.setCbu(defaultCbu);
//            defaultAccount.setAmount(BigDecimal.ZERO);
//            defaultAccount.setOwner(user);
//            accountRepository.save(defaultAccount);
            userDto = UserMapper.userToDto(entitySaved);
            return userDto;

        }

        // Devuelvo error en campo con error
        user.setEmail("ERROR: Mail existente");
        user.setDni("");
        user.setAddress("");
        user.setUsername("");
        user.setPassword("");

        return UserMapper.userToDto(user);

    }

    public String deleteUser(Long id) {
//        BAJA LOGICA, OBTENEMOS EL USUARIO A ELIMINAR Y LE SETEAMOS EL VALOR FALSE EN ENABLED
        User userToDelete = userRepository.findById(id).get();
        if (userRepository.existsById(id)) {
            userToDelete.setEnabled(false);
            userRepository.save(userToDelete);
//            userRepository.deleteById(id);
            return "El usuario con id: " + id + " ha sido eliminado";
        } else {
            return "El usuario con id: " + id + ", no existe";
        }
    }

    public UserDto updateUser(Long id, UserDto userDto) throws Exception {

        // Mapeo de userdto a user
        User user = UserMapper.dtoTouser(userDto);
        User userToModify = userRepository.findById(id).get();
        if ((userRepository.existsById(id)) && (userToModify.getEnabled() == true) && (!existsEmail(user))) {

            //NO DEBEMOS PERMITIR LA ACTUALIZACIÓN DE UN USUARIO DESHABILITADO
            // VALIDACIONES
            // Username
            if (userDto.getUsername() != null) {
                userToModify.setUsername(userDto.getUsername());
            }

            // Mail
            if (userDto.getEmail() != null) {
                userToModify.setEmail(userDto.getEmail());
            }

            // Password
            if (userDto.getPassword() != null) {
                userToModify.setPassword(userDto.getPassword());
            }

            // Dni
            if (userDto.getDni() != null) {
                userToModify.setDni(userDto.getDni());
            }

            // Address
            if (userDto.getAddress() != null) {
                userToModify.setAddress(userDto.getAddress());
            }

            // Birthday
            if (userDto.getBirthdate() != null) {
                userToModify.setBirthdate(userDto.getBirthdate());
            }

            // Enabled
            if (userDto.getEnabled() != null) {
                userToModify.setEnabled(userDto.getEnabled());
            }

            // VERIFICACION DE DATOS DUPLICADOS
//                if (!existsEmail(user)) {
            User userModified = userRepository.save(userToModify);
            return UserMapper.userToDto(userModified);
//                }

        } else {
            return null;
        }

    }


    public boolean existsEmail(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            return true;
        }

        return false;
    }

}
