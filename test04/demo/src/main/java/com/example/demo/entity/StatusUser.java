package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data

public class StatusUser {
    @SequenceGenerator(name="statususer_seq",sequenceName="statususer_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="statususer_seq")
    @Id
    private Long id;
    private String name;

    public StatusUser(){}

    public StatusUser(String name){
        this.name = name;
    }

}
