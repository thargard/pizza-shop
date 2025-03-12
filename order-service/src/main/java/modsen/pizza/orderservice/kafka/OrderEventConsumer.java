package modsen.pizza.orderservice.kafka;

import modsen.pizza.orderservice.dto.OrderResponseDto;
import modsen.pizza.orderservice.dto.OrderResponseExpandedDto;
import modsen.pizza.orderservice.dto.UserDto;
import modsen.pizza.orderservice.service.OrderService;
import modsen.pizza.orderservice.service.UserCacheService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderEventConsumer {
    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final UserCacheService userCacheService;

    public OrderEventConsumer(OrderService orderService, ModelMapper modelMapper, UserCacheService userCacheService) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.userCacheService = userCacheService;
    }

    @KafkaListener(topics = "user-give-all", groupId = "my-consumer-group")
    public void getUsersInfo(ConsumerRecord<String, List<UserDto>> record){
        for (UserDto user: record.value()){
            userCacheService.saveUser(user);
        }
        /*List<OrderResponseExpandedDto> response = new ArrayList<>();
        for (OrderResponseDto order: orderService.getAllOrders()){
            OrderResponseExpandedDto obj = modelMapper.map(order, OrderResponseExpandedDto.class);
            for (UserDto user: record.value()){
                if (user.getId().equals(order.getUserId())){
                    obj.setUsername(user.getUsername());
                    break;
                }
            }
            response.add(obj);
        }*/
    }
}
