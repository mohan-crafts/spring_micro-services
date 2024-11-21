package com.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import com.order.client.BookClient;
import com.order.client.UserClient;
import com.order.dto.Book;
import com.order.dto.User;
import com.order.entity.Order;
import com.order.repo.OrderRepo;


@Service
public class OrderService {
	
	@Autowired OrderRepo orderRepo;
	@Autowired UserClient userClient;
	@Autowired BookClient bookClient;
	
	public List<Order> getOrders() {
	
		return this.orderRepo.findAll();
	}
	
	@CircuitBreaker(name = "testService", fallbackMethod = "fallbackMethod")
	public Order getOrderById(Long orderId) {
		Order order = this.orderRepo.findById(orderId).get();
		User user = userClient.getUser(order.getUserId());
		Book book = bookClient.getBook(order.getBookId());
		order.setUser(user);
		order.setBook(book);
		return order;
	}
	
	public Order createOrder(Order order) {
		return this.orderRepo.save(order);
	}
	
	public Order updateOrder(Order order) {
		return this.orderRepo.save(order);
	}
	
	public void deleteOrder(Long orderId) {
		this.orderRepo.deleteById(orderId);
	}
	
	public String fallbackMethod(Exception e) {
        return "Order Service is Busy, Try again later";
    }
	
}