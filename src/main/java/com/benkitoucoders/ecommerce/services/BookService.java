package com.benkitoucoders.ecommerce.services;

import com.benkitoucoders.ecommerce.dao.BookRepository;
import com.benkitoucoders.ecommerce.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBooks(){
        return  bookRepository.findAll();
    }


}
