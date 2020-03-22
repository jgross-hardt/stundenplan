package org.jgrosshardt.jpa;
import org.jgrosshardt.jpa.database.Fach;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Query {

    private static EntityManager entityManager;

    public static void setup() {

        InputStream is = Query.class.getResourceAsStream("/META-INF/persistence.xml");
        try {
            String s = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(s);
        } catch (IOException ignored) {
        }

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("stundenplan");
        entityManager = factory.createEntityManager();

        Map<String, Object> properties = factory.getProperties();
        for (String s : properties.keySet()) {
            System.err.println(s + " : " + properties.get(s));
        }
    }

    public static void shutdown() {
        entityManager.close();
    }

    public <T> List<T> query(String queryString, Class<T> resultClass) {
        TypedQuery<T> query = entityManager.createQuery(queryString, resultClass);
        return query.getResultList();
    }

    public static void main(String[] args) {
        setup();
        Query q = new Query();
        List<Fach> list = q.query("select f from Fach f", Fach.class);
        System.out.println(list.get(1));
        shutdown();
    }
}
