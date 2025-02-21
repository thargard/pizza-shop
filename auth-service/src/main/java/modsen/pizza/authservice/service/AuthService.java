package modsen.pizza.authservice.service;

import modsen.pizza.authservice.entity.UserCredentials;
import modsen.pizza.authservice.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialsRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredentials creds) {
        creds.setPassword(passwordEncoder.encode(creds.getPassword()));
        repository.save(creds);
        return "saved";
    }

    public String generateToken(String name){
        return jwtService.generateToken(name);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
