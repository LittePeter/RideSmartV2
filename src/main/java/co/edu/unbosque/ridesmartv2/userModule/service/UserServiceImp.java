package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.sharedEvents.UserCreatedEvent;
import co.edu.unbosque.ridesmartv2.userModule.exception.UserNotFoundException;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.AccountState;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.User;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.VerificationToken;
import co.edu.unbosque.ridesmartv2.userModule.model.persistence.VerificationTokenRepo;
import co.edu.unbosque.ridesmartv2.userModule.model.persistence.UserRepo;
import jakarta.transaction.Transactional;
import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    private final ApplicationEventPublisher publisher;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VerificationTokenRepo verificationTokenRepo;
    @Autowired
    private ModelMapper mapper;

    public UserServiceImp(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        
        User user = mapper.map(userDto, User.class);
        user.setRole("USER");
        user.setStatus( AccountState.PENDING);
        user.setPoints(0);
        user.setBalance(0);
        user.setRole("USER");
        userRepo.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUserId(user.getIdentification());
        verificationToken.setExpiration(LocalDateTime.now().plusMinutes(10));
        verificationTokenRepo.save(verificationToken);

        publisher.publishEvent(new UserCreatedEvent(
                user.getIdentification(),
                user.getName(),
                user.getMail(),
                token
        ));
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
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUserDtoList() {
        return mapper.mapList(userRepo.findAll(), UserDto.class);
    }

    @Override
    public void activateUser(String identification) {
        User user = mapper.map(findById(identification), User.class);
        user.setStatus(AccountState.ACTIVE);
        userRepo.save(user);
    }


}
