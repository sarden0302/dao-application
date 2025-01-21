package edu.kh.com.daoapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 설정 관련 자바 클레스
public class SecurityConfig {

    /******** Bean 을 작성하는 이유 **********/
    // SecurityConfig 이외에 다른 파일에서 passwordEncoder 를 사용할 수 있도록 설정
    // 의존성 주입
    @Bean   // 비밀번호 암호화 처리 자바 기능
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // 자바에서 만든 PasswordEncoder.java 파일을 가져와 비밀번호 암호화 기능을 사용하겠다 설정


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.anyRequest().permitAll())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());
        return http.build();
    }
    /*
    CSRF (Cross-Site Request Forgery) : 공격자가 사용자의 권한을 도용해서 악의적인 요청을 서버로 보내도록 유도하는 방식
        csrf.disable() : 비활성화 -> 개발 단계에서 잠시 비활성화 처리해서 추가적인 암호기능을 개발자가 풀어내기 보다는 코드 작성에 집중 할 수 있도록 설정
    authorizeHttpRequests
        HTTP 요청에 권한을 설정하는 메서드
        .anyRequest() = 모든 요청 (모든 URL 경로로 들어오는 요청)
        .permitAll()  = 모두 허용   -> 개발 단계에서는 허용. 개발 후에는 API 접근 시 접근 불가로 설정
    formLogin(form -> form.disable())
        [build.gradle] 파일
        dependencies {
            implementation 'org.springframework.boot:spring-boot-starter-security'
        }

        spring-boot-starter-security 를 dependencies 안에 넣어 활성화를 진행하면 자동으로 보안설정이 되면서
        관리자 권한 아이디 비밀번호를 작성해야지만 사이트 이용 가능하게 초기에 설정되어 있다.

        form.disable() -> 작성 비활성화 처리함
     */
}
