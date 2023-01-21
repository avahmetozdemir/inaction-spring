package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.CreateUserRequest;
import com.lawman.inaction.user.dto.UpdateUserRequest;
import com.lawman.inaction.user.dto.UserDto;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}") //200
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping //201
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));

    }

    @PutMapping("/{id}") //202
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(id,updateUserRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Void> activateUser(@PathVariable("id") Long id) {
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
