
package com.netcracker.controllers;

import com.netcracker.dto.RequestContext;
import com.netcracker.dto.RoleTeamDTO;
import com.netcracker.dto.UserDTO;
import com.netcracker.entities.User;
import com.netcracker.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.netcracker.filters.EntryFilter.REQUEST_CONTEXT;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> updateUser(@RequestAttribute(REQUEST_CONTEXT) RequestContext requestContext,
                                           @RequestBody RoleTeamDTO roleTeamDTO) {
        return ResponseEntity.ok(userService.updateUser(requestContext, roleTeamDTO));
    }

    @GetMapping("")
    public UserDTO getUser(@RequestAttribute(REQUEST_CONTEXT) RequestContext requestContext, @RequestParam String ref) {
        return userService.getUser(requestContext, ref);
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}