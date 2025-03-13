package modsen.pizza.categoryservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CategoryEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CategoryEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCategoryDeleteEvent(Long categoryId) {
        kafkaTemplate.send("category-events", ""+categoryId);
    }
}
