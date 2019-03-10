package com.alex.jpa.hibernate.demo;

import com.alex.jpa.hibernate.demo.entities.Course;
import com.alex.jpa.hibernate.demo.repositories.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Course course = courseRepository.findById(10001L);
//		logger.info("Course 10001 info -> {}", course);
//		//courseRepository.deleteById(10001L);
//		courseRepository.save(new Course("Algorithms"));
//
//		courseRepository.testEntityManager();
		courseRepository.testEntityManager2();

//		Course course = courseRepository.findById(10001L);
//		course.setName("ASLS");
//		courseRepository.save(course);

		courseRepository.nativeQueryWithParameters();
	}
}
