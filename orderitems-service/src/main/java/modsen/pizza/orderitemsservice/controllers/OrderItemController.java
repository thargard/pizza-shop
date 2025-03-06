package modsen.pizza.orderitemsservice.controllers;

import jakarta.validation.Valid;
import modsen.pizza.orderitemsservice.dto.OrderItemDto;
import modsen.pizza.orderitemsservice.entity.OrderItem;
import modsen.pizza.orderitemsservice.mapper.OrderItemMapper;
import modsen.pizza.orderitemsservice.message.OrderProductDto;
import modsen.pizza.orderitemsservice.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrder(@Valid @RequestBody OrderItemDto dto) {
        return new ResponseEntity<>(orderItemMapper.toDto(orderItemService.save(dto)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<OrderItemDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        Page<OrderItem> orderPage = orderItemService.findAll(PageRequest.of(page, size));
        List<OrderItemDto> dtos = orderItemMapper.mapList(orderPage.getContent());

        Page<OrderItemDto> response = new PageImpl<>(dtos, orderPage.getPageable(), orderPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderItemDto> update(@RequestBody OrderItem item){
        return new ResponseEntity<>(orderItemMapper.toDto(orderItemService.update(item)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        orderItemService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/withProducts")
    public ResponseEntity<List<OrderProductDto>> getProducts(){
        return new ResponseEntity<>(orderItemService.getItemsWithProducts(), HttpStatus.OK);
    }
}
