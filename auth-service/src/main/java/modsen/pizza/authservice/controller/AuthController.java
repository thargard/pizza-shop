package modsen.pizza.authservice.controller;

import modsen.pizza.authservice.dto.AuthRequest;
import modsen.pizza.authservice.entity.User;
import modsen.pizza.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.OK);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest request){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authenticate.isAuthenticated()){
            return service.generateToken(request.getUsername());
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        service.validateToken(token);
        return "Token is valid!";
    }
}
