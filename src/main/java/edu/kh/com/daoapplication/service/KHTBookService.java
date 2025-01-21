package edu.kh.com.daoapplication.service;

import edu.kh.com.daoapplication.entity.KHTBook;
import edu.kh.com.daoapplication.repository.KHTBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KHTBookService {
    @Autowired
    KHTBookRepository khtBookRepository;

    public List<KHTBook> getAllBooks() {
        return khtBookRepository.findAll();
    }

    public KHTBook getBookById(int id) {
        return khtBookRepository.findBookById(id);
    }

    public KHTBook save(KHTBook khtBook) {
        return khtBookRepository.save(khtBook);
    }
}
