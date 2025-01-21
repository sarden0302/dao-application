package edu.kh.com.daoapplication.repository;

import edu.kh.com.daoapplication.dao.KHTUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    mapper 와 비슷한 기능

    [차이점]
    Repository
        : 쿼리 작성 없이 메서드 이름으로 동작하는 기본 CRUD 메서드 제공
        ex) 아이디 찾기 = findById
            회원 저장   = save
            회원 삭제   = delete
            전체 조회   = findAll
 */
@Repository
public interface KHTUserRepository extends JpaRepository<KHTUser, Long> {
    // mapper 로 작성했을 때 mybatis 에서 작성한 id 명칭으로 쿼리(sql)를 불러왔지만
    // repository 는 조회 저장 수정 삭제와 같은 기본 기능 탑제

    // 유저 네임을 이용해서 비밀번호 찾기
    KHTUser findByUsername(String username);

    KHTUser findById(int id);

}
