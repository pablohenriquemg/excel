package com.example.springboot;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Billionaires {

    @Id
    @GeneratedValue
    private Long id;
    private String first_name;
    private String last_name;
    private String career;
}
