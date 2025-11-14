package co.edu.unbosque.ridesmartv2.userModule.controller;

import co.edu.unbosque.ridesmartv2.userModule.exception.UserNotFoundException;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.VerificationTokenDto;
import co.edu.unbosque.ridesmartv2.userModule.service.UserService;
import co.edu.unbosque.ridesmartv2.userModule.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationService verificationService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        UserDto newUser = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestParam Long id) {
        Optional<UserDto> getUser = userService.findById(id);
        if (getUser.isEmpty()) throw new UserNotFoundException(id);
        return ResponseEntity.ok(getUser.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUserDtoList();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable String id, @RequestBody UserDto userDto) {
        userDto.setMail(id);
        return userService.update(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/verify")
    public ResponseEntity<String> confirm(@RequestParam String token) {
        Optional<VerificationTokenDto> opt = verificationService.getToken(token);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Token inválido");
        }
        if (opt.get().getExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Token expirado");
        }
        userService.activateUser(opt.get().getUserId());
        verificationService.consumeToken(token);

        return ResponseEntity.ok("Cuenta confirmada exitosamente");
    }


}

