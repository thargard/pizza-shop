package modsen.pizza.orderservice.mapper;

import modsen.pizza.orderservice.dto.OrderDto;
import modsen.pizza.orderservice.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    public OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getUserId());
        return orderDto;
    }

    public Order toOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        return order;
    }

    public List<OrderDto> toDtoList(List<Order> orders){
        List<OrderDto> dtos = new ArrayList<>();
        for (Order order: orders){
            OrderDto orderDto = new OrderDto();
            orderDto.setUserId(order.getUserId());
            dtos.add(orderDto);
        }
        return dtos;
    }
}
