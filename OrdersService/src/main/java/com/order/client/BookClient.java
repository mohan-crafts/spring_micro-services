package com.order.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.dto.Book;


@Service
public class BookClient {
	
	
	public Book getBook(Long bookId) {
		
		Book book = WebClient.create().get().uri("http://localhost:8082/books/" + bookId)
			 			.retrieve().bodyToMono(Book.class).block();
        
        return book;
	}
}