package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data

public class JobPosition {
    @SequenceGenerator(name="jobposition_seq",sequenceName="jobposition_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="jobposition_seq")
    @Id
    private Long id;
    private String name;

    public JobPosition(){}

    public JobPosition(String name){
        this.name = name;
    }

}
