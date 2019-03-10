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
public class JPQLTest {

    //SQL uses Tables
    //JPQL uses Entities

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    public void jpql_basic() {
        //List resultList = entityManager.createQuery("select c from Course c").getResultList();
        List resultList = entityManager.createNamedQuery("querry_getAll").getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    public void jpql_typed() { //better to use typedquerry than normal querry
        TypedQuery<Course> typedQuery = entityManager.createQuery("select c from Course c", Course.class);
        List resultList = typedQuery.getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    public void jpql_where() { //better to use typedquerry than normal querry
        //TypedQuery<Course> typedQuery = entityManager.createQuery("select c from Course c where id=10001 or id=10002", Course.class);
        TypedQuery<Course> typedQuery = entityManager.createNamedQuery("querry_get10001_10002", Course.class);
        List resultList = typedQuery.getResultList();
        logger.info("select c from Course c where id=10001 or id=10002 -> {}", resultList);
    }

}