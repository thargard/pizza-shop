package modsen.pizza.newservice.mapper;

import modsen.pizza.newservice.dto.UserDto;
import modsen.pizza.newservice.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public UserDto toDto(User user){
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        return user;
    }

    public List<UserDto> toDtoList(List<User> users){
        List<UserDto> dtos = new ArrayList<UserDto>();
        for (User user : users) {
            UserDto dto = new UserDto();
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());
            dto.setUsername(user.getUsername());
            dto.setRole(user.getRole());
            dtos.add(dto);
        }
        return dtos;
    }
}
