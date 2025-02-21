package modsen.pizza.authservice.service;

import modsen.pizza.authservice.entity.User;
import modsen.pizza.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "saved";
    }

    public String generateToken(String name){
        return jwtService.generateToken(name);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
