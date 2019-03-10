package com.alex.jpa.hibernate.demo.repositories;

import com.alex.jpa.hibernate.demo.DemoApplication;
import com.alex.jpa.hibernate.demo.entities.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class NativeQueriesTest {

    //Traditional SQL
    //After native transactions jpa entities should be refreshed

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    public void nativeQueries_basic() {
        List resultList = entityManager.createNativeQuery("select * from course", Course.class).getResultList();
        logger.info("select * from course -> {}", resultList);
    }


}