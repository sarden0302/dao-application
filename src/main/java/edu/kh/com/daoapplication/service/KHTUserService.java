package edu.kh.com.daoapplication.service;

import edu.kh.com.daoapplication.entity.KHTUser;
import edu.kh.com.daoapplication.repository.KHTUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KHTUserService {

    @Autowired  // @Bean 이나 @Bean 이 내장되어 있는 @Mapper @Repository @Controller @Service 등 호출하는 기능
    private PasswordEncoder passwordEncoder;    // SecurityConfig.java 내부에 작성되어 있는 기능 가져옴

    @Autowired
    private KHTUserRepository khtUserRepository;

    // 모든 유저 조회
    public List<KHTUser> findAll() {
        return khtUserRepository.findAll();     // Repository 에 기본적으로 탑제 되어 있기 때문에 선언 없이 사용 가능
    }

    // 유저 저장하기
    public KHTUser save(KHTUser khtUser) {
        System.out.println("html -> controller -> service 로 가져온 비밀번호 확인 : " + khtUser.getPassword());
        System.out.println("가져온 비밀번호 암호화" + passwordEncoder.encode(khtUser.getPassword()));

        // khtUser.getPassword() => khtUser 유저에 저장하고자 html 에서 작성한 비밀번호 가져오기
        // passwordEncoder.encode(khtUser.getPassword()) => 가져온 비밀번호 암호화
        // khtUser.setPassword => 암호화된 비밀번호를 DB에 저장
        String encodePassword = passwordEncoder.encode(khtUser.getPassword());
        khtUser.setPassword(passwordEncoder.encode(khtUser.getPassword()));

        System.out.println("암호화 완료된 비밀번호 확인 : " + encodePassword); // 위에서 작성한 암호 확인하기

        return khtUserRepository.save(khtUser);
    }

    // 아이디를 활용해서 유저 상세보기
    public KHTUser findById(int id) {
        return khtUserRepository.findById(id);
    }
}
