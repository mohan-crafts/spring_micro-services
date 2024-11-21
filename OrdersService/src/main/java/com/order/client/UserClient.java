package com.order.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.dto.User;


@Service
public class UserClient {
	
//	@Autowired WebClient webClient;
	
	public User getUser(Long userId) {
		
		User user = WebClient.create().get().uri("http://localhost:8081/users/" + userId)
			 			.retrieve().bodyToMono(User.class).block();
        
        return user;
	}
}