package modsen.pizza.userservice.service;

import modsen.pizza.userservice.dto.UserDto;
import modsen.pizza.userservice.entity.User;
import modsen.pizza.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(UserDto dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .role(dto.getRole())
                .build());
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
