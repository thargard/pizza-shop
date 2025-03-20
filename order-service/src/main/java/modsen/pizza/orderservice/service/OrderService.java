package modsen.pizza.orderservice.service;

import jakarta.persistence.EntityNotFoundException;
import modsen.pizza.orderservice.dto.*;
import modsen.pizza.orderservice.entity.Order;
import modsen.pizza.orderservice.message.OrderItemClient;
import modsen.pizza.orderservice.message.UserClient;
import modsen.pizza.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserClient userClient;
    @Autowired
    private OrderItemClient orderItemClient;
    @Autowired
    private UserCacheService userCacheService;

    public Order save(OrderDto dto) {
        Order order = new Order();
        order.setUserId(dto.getUserId());
        return orderRepository.save(order);
        //return orderRepository.save(Order.builder().userId(dto.getUserId()).build());
    }

    public OrderResponseDto createOrder(OrderRequest req){
        if (userClient.getUserById(req.getUserId()) == null) {
            throw new EntityNotFoundException("User not found");
        }
        System.out.println("Полученый объект - " + req); // проверяем что пришло

        Order preorder = new Order();
        preorder.setUserId(req.getUserId());
        preorder.setPaid(false);
        preorder.setCreatedAt(Date.valueOf(LocalDate.now())); // делает только yyyy-mm-dd нет времени
        preorder.setTotalPrice(req.getTotalPrice());
        Order order = orderRepository.save(preorder);

        orderItemClient.createOrderItems(order.getId(), req.getItems());

        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(order.getId());
        response.setUserId(req.getUserId());
        response.setItems(req.getItems());

        return response;
    }

    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        /*Page<OrderItemDto> page = orderItemClient.getOrderItems();
        List<OrderItemDto> items = page.getContent();*/
        List<OrderItemDto> items = orderItemClient.getDefault();


        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderId(order.getId());
            orderResponseDto.setUserId(order.getUserId());
            List<OrderItemRequest> requests = new ArrayList<>();
            for (OrderItemDto item : items) {
                if (item.getOrderId().equals(order.getId())){
                    OrderItemRequest itemRequest = new OrderItemRequest();
                    itemRequest.setProductId(item.getProductId());
                    itemRequest.setQuantity(item.getAmount());
                    requests.add(itemRequest);
                }
            }
            orderResponseDto.setItems(requests);
            orderResponseDtos.add(orderResponseDto);
        }
        return orderResponseDtos;
    }

    public List<OrderResponseExpandedDto> getAllExpandedOrders(){
        List<OrderResponseExpandedDto> response = new ArrayList<>();
        for (OrderResponseDto dto: getAllOrders()){
            UserDto user = userCacheService.getUser(dto.getUserId());
            if (user != null){
                OrderResponseExpandedDto rs = new OrderResponseExpandedDto();
                rs.setOrderId(dto.getOrderId());
                rs.setUsername(user.getUsername());
                rs.setItems(dto.getItems());
                response.add(rs);
            } else {
                throw new EntityNotFoundException("User not found!");
            }
        }
        return response;
    }

    public List<FinalResponseDto> getAllOrdersByUserId(Long userId) {
        if (userClient.getUserById(userId) == null) {
            throw new EntityNotFoundException("User not found");
        }

        /*List<OrderResponseDto> responses = new ArrayList<>();
        for (OrderResponseDto orderResponseDto : getAllOrders()) {
            if (orderResponseDto.getUserId().equals(userId)) {
                responses.add(orderResponseDto);
            }
        }*/
        List<OrderResponseDto> allOrders = getAllOrders();
        List<FinalResponseDto> responses = new ArrayList<>();
        for (Order order: findAll()){
            if (order.getUserId().equals(userId)){
                FinalResponseDto finalDto = new FinalResponseDto();
                finalDto.setId(order.getId());
                finalDto.setUserId(order.getUserId());
                finalDto.setTotalPrice(order.getTotalPrice());
                finalDto.setPaid(order.isPaid());
                finalDto.setCreatedAt(order.getCreatedAt());
                for (OrderResponseDto dto : allOrders){
                    if (dto.getOrderId().equals(order.getId())){
                        finalDto.setItems(dto.getItems());
                        break;
                    }
                }
                responses.add(finalDto);
            }
        }
        return responses;
    }

    public Order updateStatus(Long id){
        System.out.println("Пришел id - " + id);
        if(orderRepository.existsById(id)){
            Order order = orderRepository.findById(id).get();
            order.setPaid(true);
            return orderRepository.save(order);
        } else {
            throw new EntityNotFoundException("Order with id" + id + " not found");
        }
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
