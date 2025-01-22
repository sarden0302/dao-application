package edu.kh.com.daoapplication.service;

import edu.kh.com.daoapplication.model.vo.VerificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class VerificationService {

    @Autowired
    private JavaMailSender mailSender;

    private Map<String, String> verificationCodes = new HashMap<String, String>();
    // 네X버 or Googlx 등 이메일을 주고 받는 회사에서 회원가입을 진행하기 위해
    // 접속해서 인증번호를 받아가는 회원이 여러명일 수 있기 때문에
    // String, String = email, code 와 같이
    // 이메일을 Key(키) 명칭으로 설정하고, 이메일에 전송된 인증번호를 Value(값) 으로 묶어서
    // verificationCodes 저장


    // 1. random 으로 지정딘 숫자 6자리 만들기
    public String randomCode() {
        Random random = new Random();
        int randomNum = 100000 + random.nextInt(900000); // 6자리 랜덤 숫자 생성
        return String.valueOf(randomNum);
        // mail 기본 타입 String
    }

    // 2. 보낼 메일의 제목 내용이 들어있는 메서드
    public void sendEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage(); // 메일 보낼 때 양식 간단히 작성되어 있는 파일
        message.setTo(email); // 사용자가 작성한 이메일로 메세지 보낼 것
        message.setSubject("인증번호 보내드립니다."); // 이메일 제목
        message.setText("귀하의 인증번호 입니다 : " + code);

        mailSender.send(message);
        log.info("이메일 성공적으로 보냈습니다. {}", email);
    }

    // 3. 보낸 이메일 && 인증코드 저장하는 메서드
    public void saveEmailCode(String email, String code) {
        log.info("=== Service - Save Email Code ===");
        verificationCodes.put(email.toLowerCase(), code);
        log.info("Save Email Code : {} -> {}", email.toLowerCase(), code);
        // 보낸 이베일과 인증번호를 저장
        // 이메일은 소문자로 저장
    }

    // 4. 이메일 모두 소문자로 통일하고, 이메일과 인증코드 일치여부 확인
    // VerificationRequest -> model.vo 에 만들어놓은 email && code 저장하는 파일
    public boolean verifyCodeWithVO(VerificationRequest request) {
        log.info("=== Service - Verify Code With VO ===");
        String email = request.getEmail().toLowerCase(); // 이메일 소문자로 가져오기
        log.info("Email : {}", email);
        String inputCode = request.getCode();   // 이메일에서 인증번호를 확인한 사용자가 입력한 인증코드
        log.info("inputCode: {}", inputCode);
        String saveCode = verificationCodes.get(email); // Java 에서 랜덤으로 만든 6자리 숫자
        log.info("saveCode: {}", saveCode);
        // Java 에서 랜덤으로 만든 숫자가 키-값 형식으로 vo 객체에 저장되어 있음
        // verificationCodes 해당하는 이메일의 인증번호 가져오기
        return inputCode.equals(saveCode);
        // inputCode    = 사용자가 작성한 인증번호
        // saveCode     = 자바가 가지고 있는 인증번호
        // equals       = 일치하면 true nor false
    }


}
