package com.lawman.inaction.user;


import com.lawman.inaction.user.dto.CreateUserDetailsRequest;
import com.lawman.inaction.user.dto.UpdateUserDetailsRequest;
import com.lawman.inaction.user.dto.UserDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user-details")
public class UserDetailsController {
    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> createUserDetails(@RequestBody CreateUserDetailsRequest request){
        return ResponseEntity.ok(userDetailsService.createUserDetails(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable Long id, @RequestBody UpdateUserDetailsRequest request){
        return ResponseEntity.ok(userDetailsService.updateUserDetails(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id){
        userDetailsService.deleteUserDetails(id);
        return ResponseEntity.ok().build();//why build() ???
    }
}
