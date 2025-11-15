package co.edu.unbosque.ridesmartv2.userModule.service;

import co.edu.unbosque.ridesmartv2.config.ModelMapper;
import co.edu.unbosque.ridesmartv2.sharedEvents.UserCreatedEvent;
import co.edu.unbosque.ridesmartv2.userModule.exception.UserNotFoundException;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.AccountState;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.User;
import co.edu.unbosque.ridesmartv2.userModule.model.entity.VerificationToken;
import co.edu.unbosque.ridesmartv2.userModule.model.persistence.UserRepo;
import co.edu.unbosque.ridesmartv2.userModule.model.persistence.VerificationTokenRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {
    @Mock
    private UserRepo userRepo;

    @Mock
    private VerificationTokenRepo verificationTokenRepo;

    @Mock
    private ModelMapper mapper;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private UserServiceImp userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImp(publisher);

        ReflectionTestUtils.setField(userService, "userRepo", userRepo);
        ReflectionTestUtils.setField(userService, "verificationTokenRepo", verificationTokenRepo);
        ReflectionTestUtils.setField(userService, "mapper", mapper);
    }


    // -----------------------------
    // TEST: create()
    // -----------------------------
    @Test
    void create_shouldSaveUserGenerateTokenAndPublishEvent() {
        UserDto dto = new UserDto();
        dto.setIdentification("123");
        dto.setName("MoaTest");
        dto.setMail("test@mail.com");

        User mappedUser = new User();
        mappedUser.setIdentification("123");
        mappedUser.setName("MoaTest");
        mappedUser.setMail("test@mail.com");

        when(mapper.map(dto, User.class)).thenReturn(mappedUser);

        User savedUser = new User();
        savedUser.setIdentification("123");
        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserDto result = userService.create(dto);

        // Assert
        verify(userRepo, times(1)).save(any(User.class));
        verify(verificationTokenRepo, times(1)).save(any(VerificationToken.class));
        verify(publisher, times(1)).publishEvent(any(UserCreatedEvent.class));

        assertEquals(dto, result);
    }

    // -----------------------------
    // TEST: findById()
    // -----------------------------
    @Test
    void findById_shouldReturnOptionalUserDto_whenUserExists() {
        String id = "123";
        User user = new User();
        user.setIdentification(id);

        UserDto dto = new UserDto();
        dto.setIdentification(id);

        when(userRepo.findById(id)).thenReturn(Optional.of(user));
        when(mapper.map(Optional.of(user), UserDto.class)).thenReturn(dto);

        Optional<UserDto> result = userService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getIdentification());
    }

    @Test
    void findById_shouldReturnEmptyOptional_whenUserDoesNotExist() {
        String id = "404";

        when(userRepo.findById(id)).thenReturn(Optional.empty());
        when(mapper.map(Optional.empty(), UserDto.class)).thenReturn(null);

        Optional<UserDto> result = userService.findById(id);

        assertFalse(result.isPresent());
    }

    // -----------------------------
    // TEST: update()
    // -----------------------------
    @Test
    void update_shouldSaveUpdatedUser() {
        UserDto dto = new UserDto();
        dto.setIdentification("123");

        User mapped = new User();
        mapped.setIdentification("123");

        when(mapper.map(dto, User.class)).thenReturn(mapped);

        UserServiceImp service = userService;

        UserDto result = service.update(dto);

        verify(userRepo, times(1)).save(mapped);
        assertEquals(dto, result);
    }

    // -----------------------------
    // TEST: delete()
    // -----------------------------
    @Test
    void delete_shouldRemoveUser_whenExists() {
        when(userRepo.existsById("123")).thenReturn(true);

        userService.delete("123");

        verify(userRepo, times(1)).deleteById("123");
    }

    @Test
    void delete_shouldThrowException_whenNotFound() {
        when(userRepo.existsById("999")).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.delete("999"));
    }

    // -----------------------------
    // TEST: getAllUserDtoList()
    // -----------------------------
    @Test
    void getAllUserDtoList_shouldReturnDtoList() {
        List<User> userList = List.of(new User(), new User());
        List<UserDto> dtoList = List.of(new UserDto(), new UserDto());

        when(userRepo.findAll()).thenReturn(userList);
        when(mapper.mapList(userList, UserDto.class)).thenReturn(dtoList);

        List<UserDto> result = userService.getAllUserDtoList();

        assertEquals(2, result.size());
    }

    // -----------------------------
    // TEST: activateUser()
    // -----------------------------
    @Test
    void activateUser_shouldSetUserToActive() {
        String id = "123";

        UserDto dto = new UserDto();
        dto.setIdentification(id);

        User user = new User();
        user.setIdentification(id);

        when(userRepo.findById(id)).thenReturn(Optional.of(user));
        when(mapper.map(Optional.of(user), User.class)).thenReturn(user);

        userService.activateUser(id);

        assertEquals(AccountState.ACTIVE, user.getStatus());
        verify(userRepo, times(1)).save(user);
    }
}