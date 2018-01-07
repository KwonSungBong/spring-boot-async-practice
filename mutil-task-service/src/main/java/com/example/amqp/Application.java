package com.example.amqp;

import com.example.amqp.entity.TaskTest;
import com.example.amqp.repository.TaskTestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	CommandLineRunner demo(TaskTestRepository taskTestRepository) {
//		return args -> {
//			TaskTest taskTest = new TaskTest();
//			taskTest.setTest("taskTest setTest");
//			taskTest.setTask("taskTest setTask");
//			taskTest.setName("taskTest setName");
//			taskTestRepository.save(taskTest);
//		};
//	}

}
