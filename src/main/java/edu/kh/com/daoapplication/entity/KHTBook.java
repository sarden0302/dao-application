package edu.kh.com.daoapplication.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Entity
@ToString
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
