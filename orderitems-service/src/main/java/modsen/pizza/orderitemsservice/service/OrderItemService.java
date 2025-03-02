package modsen.pizza.orderitemsservice.service;

import jakarta.persistence.EntityNotFoundException;
import modsen.pizza.orderitemsservice.dto.OrderItemDto;
import modsen.pizza.orderitemsservice.entity.OrderItem;
import modsen.pizza.orderitemsservice.mapper.OrderItemMapper;
import modsen.pizza.orderitemsservice.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItemDto dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(dto.getOrderId());
        orderItem.setProductId(dto.getProductId());
        orderItem.setAmount(dto.getAmount());
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findAll() { return orderItemRepository.findAll(); }

    public Page<OrderItem> findAll(Pageable pageable) { return orderItemRepository.findAll(pageable); }

    public OrderItem update(OrderItem order) { return orderItemRepository.save(order); }

    public void delete(Long id) {
        if(orderItemRepository.existsById(id)){
            orderItemRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("OrderItem with id" + id + " not found");
        }

    }
}
