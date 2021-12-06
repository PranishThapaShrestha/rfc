package com.nicasia.rfc.usermanagement.user;
//

import com.nicasia.rfc.shared.succesresponse.SuccessResponse;
import com.nicasia.rfc.usermanagement.user.dto.CreateUserRequest;
import com.nicasia.rfc.usermanagement.user.dto.UserResource;
import com.nicasia.rfc.usermanagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add-user")
    public SuccessResponse addUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @GetMapping(value = "/all-users")
    public List<UserResource> getAllUserResource() {
        return userService.getAllUsers();
    }


}
