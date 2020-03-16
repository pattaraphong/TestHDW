package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class NameRegisterController{

    @Autowired
    private NameRegisterRepository nameRegisterRepository;
    @Autowired
    private JobPositionRepository jobPositionRepository;
    @Autowired
    private StatusUserRepository statusUserRepository;

    @GetMapping("/nameRegister")
    public Collection<NameRegister> nameRegister1() {
        return nameRegisterRepository.findAll();
    }

    @GetMapping("/SearchName/{name}")
    public List<NameRegister> Name(@PathVariable String name)  {
        return nameRegisterRepository.findByNameContaining(name);
    }
    @GetMapping("/SearchEmail/{email}")
    public List<NameRegister> Email(@PathVariable String email)  {
        return nameRegisterRepository.findByEmailContaining(email);
    }

    @GetMapping("/SearchPhone/{phone}")
    public List<NameRegister> Phone(@PathVariable String phone)  {
        return nameRegisterRepository.findByPhoneContaining(phone);
    }

    @GetMapping("/SearchJobAndName/{id}/{name}")
    public List<NameRegister> NameAndJob(@PathVariable Long id,@PathVariable String name)  {
        Optional<JobPosition> job = jobPositionRepository.findById(id);
        return nameRegisterRepository.findByJobPositionAndNameContaining(job.get(),name);
    }
    @GetMapping("/SearchAllJobAndEmail/{id}/{email}")
    public List<NameRegister> EmailAndJob(@PathVariable Long id,@PathVariable String email)  {
        Optional<JobPosition> job = jobPositionRepository.findById(id);
        return nameRegisterRepository.findByJobPositionAndEmailContaining(job.get(),email);
    }
    @GetMapping("/SearchAllJobAndPhone/{id}/{phone}")
    public List<NameRegister> PhoneAndJob(@PathVariable Long id,@PathVariable String phone)  {
        Optional<JobPosition> job = jobPositionRepository.findById(id);
        return nameRegisterRepository.findByJobPositionAndPhoneContaining(job.get(),phone);
    }



 
    @GetMapping("/SearchJob/{id}")
    public List<NameRegister> Job(@PathVariable Long id)  {
        Optional<JobPosition> job = jobPositionRepository.findById(id);
        return nameRegisterRepository.findByJobPosition(job.get());
    }
    /*
    @GetMapping("/{name}")
    public List<NameRegister> nameRegister2(@PathVariable String name)  {
        return nameRegisterRepository.findByName(name);

    */


    /*
    @GetMapping("/nameRegister/{name}")
    public Collection<NameRegister> nameRegister2() {
        return nameRegisterRepository.findByName();
    }
    @GetMapping("/nameRegister/{email}")
    public Collection<NameRegister> nameRegister3() {
        return nameRegisterRepository.findByEmail();
    }
    @GetMapping("/nameRegister/{phone}")
    public Collection<NameRegister> nameRegister4() {
        return nameRegisterRepository.findByPhone();
    }
    */
    @GetMapping("/nameRegister/jobp")
    public Collection<NameRegister> nameRegister() {
        return nameRegisterRepository.findAll();
    }





    @GetMapping("/jobPosition")
    public Collection<JobPosition> jobPosition() {
        return jobPositionRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/statusUser")
    public Collection<StatusUser> statusUser() {
       return statusUserRepository.findAll();
    }

    
    /*@PostMapping("/nameRegisterV2")
    public NameRegister newNameRegister(NameRegister newNameRegister, @RequestBody() Map<String,Object> body){
        Optional<JobPosition> jobPosition = jobPositionRepository.findById((Long.valueOf( body.get("jobPosition").toString() )));
        Optional<StatusUser> statusUser = statusUserRepository.findById((Long.valueOf( body.get("statusUser").toString() )));
        newNameRegister.setDate(new Date(Long.valueOf(body.get("date").toString())));
        newNameRegister.setName(body.get("name").toString());
        newNameRegister.setEmail(body.get("email").toString());
        newNameRegister.setPhone(body.get("phone").toString());
        newNameRegister.setJobPosition(jobPosition.get());
        newNameRegister.setDetail(body.get("detail").toString());
        newNameRegister.setStatusUser(statusUser.get());
        return nameRegisterRepository.save(newNameRegister);
    }*/

    @PostMapping("/nameRegister/addNameRegister/{name}/{email}/{phone}/{jobPositionId}/{detail}/{statusUserId}")
    public NameRegister newNameRegister(@PathVariable String name, @PathVariable String email,@PathVariable String phone,
                                        @PathVariable Long jobPositionId,@PathVariable String detail,@PathVariable Long statusUserId) {
        NameRegister newNameRegister = new NameRegister();
        JobPosition jobposition = jobPositionRepository.findById(jobPositionId).get();
        StatusUser statususer = statusUserRepository.findById(statusUserId).get();
        newNameRegister.setDate(new Date());
        newNameRegister.setName(name);
        newNameRegister.setEmail(email);
        newNameRegister.setPhone(phone);
        newNameRegister.setJobPosition(jobposition);
        newNameRegister.setDetail(detail);
        newNameRegister.setStatusUser(statususer);
        return nameRegisterRepository.save(newNameRegister);
    }
    /*
    @PostMapping("/nameRegister/addNameRegister/{name}/{email}/{phone}/{jobPositionId}/{detail}/{statusUserId}")
    public NameRegister newNameRegister(@PathVariable String name, @PathVariable String email,@PathVariable String phone,
                                        @PathVariable Long jobPositionId,@PathVariable String detail,@PathVariable Long statusUserId) {
        NameRegister newNameRegister = new NameRegister();
        JobPosition jobposition = jobPositionRepository.findById(jobPositionId).get();
        StatusUser statususer = statusUserRepository.findById(statusUserId).get();
        newNameRegister.setName(name);
        newNameRegister.setEmail(email);
        newNameRegister.setPhone(phone);
        newNameRegister.setJobPosition(jobposition);
        newNameRegister.setDetail(detail);
        newNameRegister.setStatusUser(statususer);
        return nameRegisterRepository.save(newNameRegister);
    }*/
    //@PutMapping("/nameRegister/addNameRegister/{NameRegisterId}/{date}/{name}/{email}/{phone}/{jobPositionId}/{detail}/{statusUserId}")
    @PutMapping("/nameRegister/addNameRegister/{NameRegisterId}/{name}/{email}/{phone}/{jobPositionId}/{detail}/{statusUserId}")
    public NameRegister putNameRegister(@PathVariable Long NameRegisterId,@PathVariable String name, @PathVariable String email,
        @PathVariable String phone,@PathVariable Long jobPositionId,@PathVariable String detail,@PathVariable Long statusUserId) {
        NameRegister putNameRegister = nameRegisterRepository.findById(NameRegisterId).get();
        JobPosition jobposition = jobPositionRepository.findById(jobPositionId).get();
        StatusUser statususer = statusUserRepository.findById(statusUserId).get();
       // newNameRegister.setDate(date = new Date());
        putNameRegister.setName(name);
        putNameRegister.setEmail(email);
        putNameRegister.setPhone(phone);
        putNameRegister.setJobPosition(jobposition);
        putNameRegister.setDetail(detail);
        putNameRegister.setStatusUser(statususer);
        return nameRegisterRepository.save(putNameRegister);
    }
/*
    @DeleteMapping("/nameRegister/{NameRegisterId}/{name}/{email}/{phone}/{jobPositionId}/{detail}/{statusUserId}")
    public NameRegister newNameRegister(@PathVariable Long NameRegisterId,@PathVariable String name, @PathVariable String email,
                                        @PathVariable String phone,@PathVariable Long jobPositionId,@PathVariable String detail,@PathVariable Long statusUserId) {
        NameRegister deleteNameRegister = nameRegisterRepository.findById(NameRegisterId).get();
        JobPosition jobposition = jobPositionRepository.findById(jobPositionId).get();
        StatusUser statususer = statusUserRepository.findById(statusUserId).get();
       // newNameRegister.setDate(date = new Date());
       deleteNameRegister.setName(name);
       deleteNameRegister.setEmail(email);
       deleteNameRegister.setPhone(phone);
       deleteNameRegister.setJobPosition(jobposition);
       deleteNameRegister.setDetail(detail);
       deleteNameRegister.setStatusUser(statususer);
        return nameRegisterRepository.save(deleteNameRegister);
    }
    */
    
    @DeleteMapping("/nameRegister/{NameRegisterId}")
    public void deleteNameRegister(@PathVariable Long NameRegisterId) {
         nameRegisterRepository.deleteById(NameRegisterId);
    }
    
}