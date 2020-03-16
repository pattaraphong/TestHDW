package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
//import java.sql.Timestamp;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;


@Entity
@Data
@ToString


public class NameRegister {
    @SequenceGenerator(name="nameregister_seq",sequenceName="nameregister_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="nameregister_seq")
    @Id
    private Long id;

    @JsonFormat(pattern="E MM/dd/yyyy HH:mm:ss aa")
    private Date date;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "[ก-์|A-z|\\s].+")
    private String name;

    @Pattern(regexp="^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    @Pattern(regexp="\\d{10}")
    private String phone;

    @ManyToOne
    JobPosition jobPosition;
    
    private String detail;
    
    @ManyToOne
    StatusUser statusUser;

    public NameRegister(){}

    public NameRegister(Date date,String name,String email,String phone,JobPosition jobPosition,String detail,StatusUser statusUser){
        this.date = date;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.jobPosition = jobPosition;
        this.detail = detail;
        this.statusUser = statusUser;
    }

}
