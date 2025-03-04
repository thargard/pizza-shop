package modsen.pizza.orderservice.controllers;

import jakarta.validation.Valid;
import modsen.pizza.orderservice.dto.OrderDto;
import modsen.pizza.orderservice.entity.Order;
import modsen.pizza.orderservice.mapper.OrderMapper;
import modsen.pizza.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto dto) {
        return new ResponseEntity<>(orderMapper.toOrderDto(orderService.save(dto)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<OrderDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Page<Order> orderPage = orderService.findAll(PageRequest.of(page, size));
        List<OrderDto> dtos = orderMapper.toDtoList(orderPage.getContent());

        Page<OrderDto> response = new PageImpl<>(dtos, orderPage.getPageable(), orderPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderDto> update(@Valid @RequestBody Order order){
        return new ResponseEntity<>(orderMapper.toOrderDto(orderService.update(order)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        orderService.delete(id);
        return HttpStatus.OK;
    }
}
