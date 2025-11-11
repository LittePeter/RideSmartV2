package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;

import java.util.Optional;

public interface UserService {
    UserDto create(UserDto userDto);
    Optional<UserDto> findById(String userId);
    UserDto update(UserDto userDto);
    UserDto delete(UserDto userDto);
}
