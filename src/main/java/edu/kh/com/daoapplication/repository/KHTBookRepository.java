package edu.kh.com.daoapplication.repository;

import edu.kh.com.daoapplication.model.entity.KHTBook;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KHTBookRepository extends JpaRepository<KHTBook, Long> {
    // save
    //  findAll
    // findBy = where
//  @Select("SELECT * FROM khtbook WHERE id= #{id}")
    // find(*) By = WHERE
    KHTBook findBookById(int id);
//  FROM 테이블명     findBy = WHERE 명칭
    // 아이디와 이름으로 비밀번호 찾기
//  @Select("SELECT password FROM khtbook WHERE id = #{id} AND name = #{name}")
    // String findPasswordByIdAndName(int id, String name);

    // 기존에 JPA에서 만들었던 save 메서드를 변형해서 재설정
    // @Override
    //KHTBook save(String title, String author, String genre, String imagePath);
}
