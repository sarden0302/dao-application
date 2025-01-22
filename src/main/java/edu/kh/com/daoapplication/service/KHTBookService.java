package edu.kh.com.daoapplication.service;

import edu.kh.com.daoapplication.entity.KHTBook;
import edu.kh.com.daoapplication.repository.KHTBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class KHTBookService {

    @Autowired
    private KHTBookRepository khtBookRepository;

    @Value("D://Leo/SoftwareEngineering/book-image-path/images/books") // application.properties 에 작성한 이미지 경로 가져옴
    private String uploadImg; // 가져온 경로는 uploadImg 공간 안에 담아줌

    public List<KHTBook> getAllBooks() {
        return khtBookRepository.findAll();
    }

    /**
     * 글자 타입의 데이터만 저장할 때 사용하는 방법
     * @param id
     * @return
     */
    public KHTBook getBookById(int id) {
        return khtBookRepository.findBookById(id);
    }

    public KHTBook save(String title, String author, String genre, MultipartFile file) {
        // 0. 저장할 파일 이름 불러오기
        // A 유저 -> 이미지 이름=다운로드.jpg B유저 -> 이미지 이름=다운로드.jpg
        // 1. 현재시간 추가       2. UUID 랜덤 이름 명칭 생성
        String filename = System.currentTimeMillis() + "_ " +  file.getOriginalFilename();

        //                          경로   + 파일명칭
        File saveFile = new File(uploadImg, filename);

        // 1. 이미지 저장하기
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            System.out.println("이미지 저장 실패!");
        }
        // 2. 이미지 경로 String 타입으로 저장하기
        KHTBook khtBook = new KHTBook();
        khtBook.setTitle(title);
        khtBook.setAuthor(author);
        khtBook.setGenre(genre);
        khtBook.setImagePath("/images/" + filename);

        return khtBookRepository.save(khtBook);
    }


}
