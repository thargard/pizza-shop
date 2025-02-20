package modsen.pizza.userservice.controllers;

import modsen.pizza.userservice.dto.UserDto;
import modsen.pizza.userservice.entity.User;
import modsen.pizza.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestParam UserDto dto) {
        return new ResponseEntity<>(userService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> readAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestParam User user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping
    public HttpStatus delete(@RequestParam Long id) {
        userService.delete(id);
        return HttpStatus.OK;
    }
}
