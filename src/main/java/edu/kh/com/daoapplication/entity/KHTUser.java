package edu.kh.com.daoapplication.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
@Entity
public class KHTUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false) // nullable=false -> null 허용 x
    private String username;

    @Column(unique=false, nullable=false) // unique=false -> 동일한 value 허용
    private String password;

    // 이미지 경로 설정
    private String imagePath;


}
