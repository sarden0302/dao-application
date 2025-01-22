package edu.kh.com.daoapplication.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationRequest {
    // 회원가입을 진행할 때 사용자가 작성한 인증번호를
    // DB 에 값을 저자하지 않고, 자바 프로그램 내에서만 값을 저장하는 형태
    // Mapper DAO Repository 통할 이유가 없음! Service 필요!
    private String email;
    private String code;


}
