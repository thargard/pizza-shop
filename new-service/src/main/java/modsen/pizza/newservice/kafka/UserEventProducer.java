package modsen.pizza.newservice.kafka;

import modsen.pizza.newservice.entity.User;
import modsen.pizza.newservice.service.UserService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEventProducer {
    private final KafkaTemplate<String, List<User>> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, List<User>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserInfo(List<User> users) {
        kafkaTemplate.send("user-give-all", users);
    }
}
