package com.example.demo.repository;

import com.example.demo.entity.NameRegister;
import com.example.demo.entity.JobPosition;
import com.example.demo.entity.StatusUser;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.Collection;
import java.util.Optional;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface NameRegisterRepository extends JpaRepository<NameRegister,Long> {
    List<NameRegister> findByJobPosition(JobPosition jobposition);
  
    //List<NameRegister> findByEmailIdInAndPincodeIn(List<String> name,List<String> email, List<String> phone);
    List<NameRegister> findByNameContaining(String name);
    List<NameRegister> findByEmailContaining(String email);
    List<NameRegister> findByPhoneContaining(String phone);

    List<NameRegister> findByJobPositionAndNameContaining(JobPosition jobposition,String name);
    List<NameRegister> findByJobPositionAndEmailContaining(JobPosition jobposition,String email);
    List<NameRegister> findByJobPositionAndPhoneContaining(JobPosition jobposition,String phone);
    
}