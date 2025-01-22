package edu.kh.com.daoapplication.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
@Entity
public class KHTBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false)
    private String title;

    @Column(unique=false, nullable=false)
    private String author;

    @Column(unique=false, nullable=false)
    private String genre;
}
