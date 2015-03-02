package com.epam.idea.rest.controller;

import com.epam.idea.core.model.User;
import com.epam.idea.core.service.UserService;
import com.epam.idea.rest.resource.UserResource;
import com.epam.idea.rest.resource.asm.UserResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public HttpEntity<UserResource> create(@Valid @RequestBody final UserResource userRes) {
        User savedUser = userService.save(userRes.toUser());
        return new ResponseEntity<>(new UserResourceAsm().toResource(savedUser), HttpStatus.CREATED);
	}

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<UserResource>> showAll() {
        List<User> userList = userService.findAll();
        List<UserResource> userResources = new UserResourceAsm().toResources(userList);
        return new ResponseEntity<>(userResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public HttpEntity<UserResource> show(@PathVariable long userId) {
        User user = userService.findOne(userId);
        UserResource res = new UserResourceAsm().toResource(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public HttpEntity<UserResource> update(@Valid @RequestBody final UserResource userRes, @PathVariable long userId) {
        User user = userService.findOne(userId);
        UserResource res = new UserResourceAsm().toResource(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public HttpEntity<UserResource> delete(@PathVariable long userId) {
        User user = userService.deleteById(userId);
        UserResource res = new UserResourceAsm().toResource(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
