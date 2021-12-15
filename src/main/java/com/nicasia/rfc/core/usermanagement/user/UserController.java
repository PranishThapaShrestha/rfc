package com.nicasia.rfc.core.usermanagement.user;
//

import com.nicasia.rfc.shared.succesresponse.SuccessResponse;
import com.nicasia.rfc.core.usermanagement.user.dto.CreateUserRequest;
import com.nicasia.rfc.core.usermanagement.user.dto.UserResource;
import com.nicasia.rfc.core.usermanagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public SuccessResponse addUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @GetMapping(value = "/users")
    public List<UserResource> getAllUserResource() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/users/{id}")
    public UserResource getUserById(@PathVariable(value = "user-id") Long id) {
        return userService.findUserResourceById(id);
    }

}
