package modsen.pizza.authservice.config;

import modsen.pizza.authservice.entity.User;
import modsen.pizza.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> credentials =  repository.findByUsername(username);
        return credentials.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found with name " + username));
    }
}
