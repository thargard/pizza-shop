package modsen.pizza.userservice.service;

import modsen.pizza.userservice.dto.UserDto;
import modsen.pizza.userservice.entity.User;
import modsen.pizza.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        return userRepository.save(user);
    }

    public List<User> findAll() { return userRepository.findAll(); }

    public User update(User user) { return userRepository.save(user); }

    public void delete(Long id) { userRepository.deleteById(id); }
}
