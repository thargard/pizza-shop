package modsen.pizza.orderservice.service;

import jakarta.persistence.EntityNotFoundException;
import modsen.pizza.orderservice.dto.OrderDto;
import modsen.pizza.orderservice.entity.Order;
import modsen.pizza.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order save(OrderDto dto) {
        Order order = new Order();
        order.setUserId(dto.getUserId());
        return orderRepository.save(order);
        //return orderRepository.save(Order.builder().userId(dto.getUserId()).build());
    }

    public List<Order> findAll() { return orderRepository.findAll(); }

    public Page<Order> findAll(Pageable pageable) { return orderRepository.findAll(pageable); }

    public Order update(Order order) { return orderRepository.save(order); }

    public void delete(Long id) {
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Order with id" + id + " not found");
        }

    }
}
