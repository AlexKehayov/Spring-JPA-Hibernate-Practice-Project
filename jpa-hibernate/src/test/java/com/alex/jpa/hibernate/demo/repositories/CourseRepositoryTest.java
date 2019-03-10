package com.alex.jpa.hibernate.demo.repositories;

import com.alex.jpa.hibernate.demo.DemoApplication;
import com.alex.jpa.hibernate.demo.entities.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findById_basic() {
        Course course = courseRepository.findById(10001L);
        assertEquals("Maths", course.getName());
    }

    @Test
    @DirtiesContext //after execution data is reset
    public void deleteById_basic() {
        courseRepository.deleteById(10002L);
        assertNull(courseRepository.findById(10002L));
    }

    @Test
    @DirtiesContext //after execution data is reset
    public void save_basic() {
        Course course = courseRepository.findById(10001L);
        course.setName("test");
        courseRepository.save(course);
        assertEquals( "test", courseRepository.findById(10001L).getName());
    }

    @Test
    @DirtiesContext
    public void testEntityManager_basic() {
    courseRepository.testEntityManager();
    }

    @Test
    @DirtiesContext
    public void testEntityManager2_basic() {
        courseRepository.testEntityManager2();
    }
}