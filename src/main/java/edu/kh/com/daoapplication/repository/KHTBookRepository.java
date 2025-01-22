package edu.kh.com.daoapplication.repository;

import edu.kh.com.daoapplication.entity.KHTBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface KHTBookRepository extends JpaRepository<KHTBook, Long> {
    // save
    //  findAll
    KHTBook findBookById(int id);

    // 기존에 JPA에서 만들었던 save 메서드를 변형해서 재설정
    // @Override
    //KHTBook save(String title, String author, String genre, String imagePath);
}
