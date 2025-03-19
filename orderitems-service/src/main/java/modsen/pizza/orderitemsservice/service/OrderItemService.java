package modsen.pizza.orderitemsservice.service;

import jakarta.persistence.EntityNotFoundException;
import modsen.pizza.orderitemsservice.dto.OrderItemDto;
import modsen.pizza.orderitemsservice.dto.OrderItemRequest;
import modsen.pizza.orderitemsservice.entity.OrderItem;
import modsen.pizza.orderitemsservice.message.ComplexProductDto;
import modsen.pizza.orderitemsservice.message.OrderProductDto;
import modsen.pizza.orderitemsservice.message.ProductClient;
import modsen.pizza.orderitemsservice.repository.OrderItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductClient productClient;

    public OrderItem save(OrderItemDto dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(dto.getOrderId());
        orderItem.setProductId(dto.getProductId());
        orderItem.setQuantity(dto.getAmount());
        return orderItemRepository.save(orderItem);
    }

    public void createOrderItems(Long orderId, List<OrderItemRequest> items){
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest item : items) {
            if (productClient.getProductById(item.getProductId())==null){
                throw new EntityNotFoundException("Product not found");
            }
            System.out.println("Получен продукт - " + item);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);
    }

    public List<OrderItem> findAll() { return orderItemRepository.findAll(); }

    public Page<OrderItem> findAll(Pageable pageable) { return orderItemRepository.findAll(pageable); }

    public OrderItem update(OrderItem order) { return orderItemRepository.save(order); }

    public void delete(Long id) {
        if(orderItemRepository.existsById(id)){
            orderItemRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("OrderItem with id " + id + " not found");
        }
    }

    public List<OrderProductDto> getItemsWithProducts(){
        List<ComplexProductDto> cpds = productClient.getProducts();
        List<OrderItemDto> items = new ArrayList<>();

        for (OrderItem order: findAll()){
            OrderItemDto dto = modelMapper.map(order, OrderItemDto.class);
            items.add(dto);
        }

        List<OrderProductDto> dtos = new ArrayList<>();

        for (ComplexProductDto cpd: cpds){
            OrderProductDto opd = modelMapper.map(cpd, OrderProductDto.class);
            for (OrderItemDto item: items){
                if (cpd.getId().equals(item.getProductId())){
                    opd.setOrderId(item.getOrderId());
                    opd.setAmount(item.getAmount());
                    break;
                }
            }
            dtos.add(opd);
        }
        return dtos;
    }
}
