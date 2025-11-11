package co.edu.unbosque.ridesmartv2.userModule.controller;
import co.edu.unbosque.ridesmartv2.userModule.exception.UserNotFoundException;
import co.edu.unbosque.ridesmartv2.userModule.model.dto.UserDto;
import co.edu.unbosque.ridesmartv2.userModule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto>create(@RequestBody UserDto userDto) {
        UserDto newUser = userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestParam String id) {
        Optional<UserDto> getUser = userService.findById(id);
        if (getUser.isEmpty()) throw new UserNotFoundException(id);
        return ResponseEntity.ok(getUser.get());
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable String id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.update(userDto);
        return userService.update(updatedUser);
    }
}

