package modsen.pizza.orderservice.service;

import modsen.pizza.orderservice.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserCacheService {
    private final Map<Long, UserDto> userCache = new HashMap<>();

    public void saveUser(UserDto user){
        userCache.put(user.getId(), user);
    }

    public UserDto getUser(Long id){
        return userCache.get(id);
    }
}
