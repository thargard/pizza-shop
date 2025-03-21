package modsen.pizza.newservice.service;

import jakarta.persistence.EntityNotFoundException;
import modsen.pizza.newservice.dto.UserDto;
import modsen.pizza.newservice.entity.User;
import modsen.pizza.newservice.kafka.UserEventProducer;
import modsen.pizza.newservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserEventProducer userEventProducer;

    public User save(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        return userRepository.save(user);
    }

    public List<User> findAll() {
        //userEventProducer.sendUserInfo(userRepository.findAll());
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable) {
        //userEventProducer.sendUserInfo(userRepository.findAll());
        return userRepository.findAll(pageable);
    }

    public User update(User user) { return userRepository.save(user); }

    public User findById(Long id) {
        if(userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    public void delete(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }
}
