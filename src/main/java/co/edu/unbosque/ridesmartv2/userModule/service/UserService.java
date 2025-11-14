package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto create(UserDto userDto);
    Optional<UserDto> findById(Long userId);
    UserDto update(UserDto userDto);
    void delete(Long id);
    List<UserDto> getAllUserDtoList();
    void activateUser(long identification);
}
