package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Order;
import com.order.service.OrderProducerService;
import com.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired OrderService orderService;
	@Autowired OrderProducerService orderProducerService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getOrders() {
		
		return ResponseEntity.ok(this.orderService.getOrders());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Long orderId) {
		
		return ResponseEntity.ok(this.orderService.getOrderById(orderId));
	}
	
	@PostMapping("")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		orderProducerService.sendMessage("Order Creation request");
		
		return ResponseEntity.ok(this.orderService.createOrder(order));
	}
	
	@PutMapping("")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
		
		return ResponseEntity.ok(this.orderService.updateOrder(order));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOrder(@PathVariable("id") Long orderId) {
		this.orderService.deleteOrder(orderId);
		return ResponseEntity.ok(true);
	}
}