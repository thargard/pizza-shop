package modsen.pizza.authservice.service;

import modsen.pizza.authservice.dto.UserDto;
import modsen.pizza.authservice.entity.User;
import modsen.pizza.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User create(UserDto dto){
        return repository.save(User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getName())
                .role(dto.getRole())
                .build());
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public User update(User user){
        return repository.save(user);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
