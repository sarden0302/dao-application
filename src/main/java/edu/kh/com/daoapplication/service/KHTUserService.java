package edu.kh.com.daoapplication.service;

import edu.kh.com.daoapplication.entity.KHTBook;
import edu.kh.com.daoapplication.entity.KHTUser;
import edu.kh.com.daoapplication.repository.KHTUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class KHTUserService {

    @Autowired  // @Bean 이나 @Bean 이 내장되어 있는 @Mapper @Repository @Controller @Service 등 호출하는 기능
    private PasswordEncoder passwordEncoder;    // SecurityConfig.java 내부에 작성되어 있는 기능 가져옴

    @Autowired
    private KHTUserRepository khtUserRepository;

    @Value("D://Leo/SoftwareEngineering/book-image-path/images/users") // application.properties 에 작성한 이미지 경로 가져옴
    private String uploadImg; // 가져온 경로는 uploadImg 공간 안에 담아줌

    // 모든 유저 조회
    public List<KHTUser> findAll() {
        return khtUserRepository.findAll();     // Repository 에 기본적으로 탑제 되어 있기 때문에 선언 없이 사용 가능
    }

    // 유저 저장하기
    public KHTUser save(String password, String username, MultipartFile file) {
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
        KHTUser khtUser = new KHTUser();
        khtUser.setUsername(username);
        khtUser.setPassword(passwordEncoder.encode(password));
        khtUser.setImagePath("/images/" + filename);

        return khtUserRepository.save(khtUser);
    }

    // 아이디를 활용해서 유저 상세보기
    public KHTUser findById(int id) {
        return khtUserRepository.findById(id);
    }
}
