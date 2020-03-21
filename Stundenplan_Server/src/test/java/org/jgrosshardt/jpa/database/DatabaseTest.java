package org.jgrosshardt.jpa.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest {

    private static EntityManager entityManager;

    @BeforeClass
    public static void setup() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("stundenplan");
        entityManager = factory.createEntityManager();
        // Employee employee = new Employee();
        // employee.setEmpNumber("hans");
        // employee.setAge(15);
        // employee.setName("Hans");
        // entityManager.getTransaction().begin();
        // entityManager.persist(employee);
        // entityManager.getTransaction().commit();
    }

    @Test
    public void testStufen() {
        TypedQuery<Stufe> stufen = entityManager.createQuery("select s From Stufe s", Stufe.class);
        List<Stufe> results = stufen.getResultList();
        for (Stufe stufe : results) {
            System.err.println(stufe);
        }
    }
}
