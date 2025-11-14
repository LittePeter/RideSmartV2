package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.userModule.exception.UserNotFoundException;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.AccountState;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.User;
import co.edu.unbosque.ridesmartv2.userModule.model.persistence.UserRepo;
import jakarta.transaction.Transactional;
import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        
        User user = mapper.map(userDto, User.class);
        user.setRole("USER");
        user.setStatus(AccountState.PENDING);
        user.setPoints(0);
        user.setBalance(0);
        userRepo.save(user);
        return userDto;
    }

    @Override
    @Transactional
    public Optional<UserDto> findById(String userId) {
        UserDto user = mapper.map(userRepo.findById(userId), UserDto.class);
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        User updatedUser = mapper.map(userDto, User.class);
        userRepo.save(updatedUser);
        return userDto;
    }

    @Override
    public void delete(String id) {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
        userRepo.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUserDtoList() {
        return mapper.mapList(userRepo.findAll(), UserDto.class);
    }
}
