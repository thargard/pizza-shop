package modsen.pizza.orderitemsservice.mapper;

import modsen.pizza.orderitemsservice.dto.OrderItemDto;
import modsen.pizza.orderitemsservice.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapper {
    public OrderItemDto toDto(OrderItem item){
        OrderItemDto dto = new OrderItemDto();
        dto.setOrderId(item.getOrderId());
        dto.setProductId(item.getProductId());
        dto.setAmount(item.getAmount());
        return dto;
    }

    public OrderItem fromDto(OrderItemDto dto){
        OrderItem item = new OrderItem();
        item.setOrderId(dto.getOrderId());
        item.setProductId(dto.getProductId());
        item.setAmount(dto.getAmount());
        return item;
    }

    public List<OrderItemDto> mapList(List<OrderItem> items){
        List<OrderItemDto> dtos = new ArrayList<>();
        for (OrderItem item: items){
            OrderItemDto dto = new OrderItemDto();
            dto.setOrderId(item.getOrderId());
            dto.setProductId(item.getProductId());
            dto.setAmount(item.getAmount());
            dtos.add(dto);
        }
        return dtos;
    }
}
