package modsen.pizza.productservice.kafka;

import modsen.pizza.productservice.service.ProductService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductEventConsumer {
    private final ProductService productService;

    public ProductEventConsumer(ProductService productService) {
        this.productService = productService;
    }

    @KafkaListener(topics = "category-events", groupId = "my-consumer-group")
    public void categoryDeleteEvent(ConsumerRecord<String, String> record){
        System.out.println("Получено событие: " + record.value());
        Long id = Long.parseLong(record.value());
        productService.deleteByCategoryId(id);
        System.out.println("Все продукты с удаленной категорией были удалены!");
    }
}
