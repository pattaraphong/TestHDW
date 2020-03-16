package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private JobPositionRepository jobPositionRepository;
	@Autowired
	private StatusUserRepository statusUserRepository;
	@Autowired
	private NameRegisterRepository nameRegisterRepository;

	@Bean
	ApplicationRunner init() {
		return args -> {
			createExample();
		};
	}

	private void createExample(){
		JobPosition student = new JobPosition("นักศึกษาสหกิจ / ฝึกงาน");
		JobPosition student2 = new JobPosition("Backend");
		StatusUser pass = new StatusUser("ผ่าน");
		StatusUser nopass = new StatusUser("ไม่ผ่าน");
		jobPositionRepository.save(student);
		jobPositionRepository.save(student2);
		statusUserRepository.save(pass);
		statusUserRepository.save(nopass);
		nameRegisterRepository.save(new NameRegister(new Date(),"dfdaffr","gghot@hotlike.com","0801234567",student,"hellomyname",pass));
		nameRegisterRepository.save(new NameRegister(new Date(),"kitty","gghot@boruka.com","0701234567",student2,"hisadaga",pass));
		nameRegisterRepository.save(new NameRegister(new Date(),"Araya","gghot@borka.com","0601234567",student,"สวัสดีค่ะดิฉันสนใจฝึกงานที่บริษัทแห่งนี้ค่ะ ขอบคุณค่ะ",pass));
	}

	
	

}
