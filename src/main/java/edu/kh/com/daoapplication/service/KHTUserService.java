package edu.kh.com.daoapplication.service;

import edu.kh.com.daoapplication.dao.KHTUser;
import edu.kh.com.daoapplication.repository.KHTUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KHTUserService {

    @Autowired
    private KHTUserRepository khtUserRepository;

    // 모든 유저 조회
    public List<KHTUser> findAll() {
        return khtUserRepository.findAll();     // Repository 에 기본적으로 탑제 되어 있기 때문에 선언 없이 사용 가능
    }

    // 유저 저장하기
    public KHTUser save(KHTUser khtUser) {
        return khtUserRepository.save(khtUser);
    }

    // 아이디를 활용해서 유저 상세보기
    public KHTUser findById(int id) {
        return khtUserRepository.findById(id);
    }
}
