package com.alex.jpa.hibernate.demo.repositories;

import com.alex.jpa.hibernate.demo.entities.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional //be able to modify or delete data
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id){
        return entityManager.find(Course.class, id);
    }

    public void deleteById(Long id){
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    public Course save(Course course){
        if(course.getId()==null){
            entityManager.persist(course);
        }else{
            entityManager.merge(course);
        }
        return course;
    }

    public void testEntityManager(){
        logger.info("Testing Entity Manager - Start");
        Course course = new Course("Sport");
        entityManager.persist(course);
        course.setName("Sport 2");
        //entityManager.merge(course); //not needed, EM keeps track because it's a single transaction
    }

    public void testEntityManager2(){
        Course course1 = new Course("OOP");
        Course course2 = new Course("Android");

        entityManager.persist(course1);
        entityManager.flush(); // saves previous operations in db

        entityManager.persist(course2);
        entityManager.flush();

        entityManager.detach(course2); //entity manager will no longer keep track of course 2

        //entityManager.clear(); //detaches all the entities

        course1.setName("OOP 2"); //will be changed
        course2.setName("Android 2"); //won't be changed

        entityManager.refresh(course1); //will be set to value saved in the db

        entityManager.flush();
    }

    public void nativeQueryWithParameters() {
        Query nativeQuery = entityManager.createNativeQuery("select * from course where id=?", Course.class);
        nativeQuery.setParameter(1, 10002);
        List resultList = nativeQuery.getResultList();
        logger.info("select * from course where id=? -> {}", resultList);
    }
}
