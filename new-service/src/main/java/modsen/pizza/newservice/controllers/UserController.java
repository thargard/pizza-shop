package modsen.pizza.newservice.controllers;

import jakarta.validation.Valid;
import modsen.pizza.newservice.dto.UserDto;
import modsen.pizza.newservice.entity.User;
import modsen.pizza.newservice.mapper.UserMapper;
import modsen.pizza.newservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(modelMapper.map(userService.save(userDto), UserDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(userMapper.toDtoList(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<UserDto>> findPage(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        Page<User> userPage = userService.findAll(PageRequest.of(page, size));
        List<UserDto> userDtos = userMapper.toDtoList(userPage.getContent());

        Page<UserDto> response = new PageImpl<>(userDtos, userPage.getPageable(), userPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@Valid @RequestBody User user) {
        return new ResponseEntity<>(modelMapper.map(userService.update(user), UserDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        userService.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}
