package org.example;


import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("Cascade user 2", 23);
            Item item1 = new Item("Cascading item 1");
            Item item2 = new Item("Cascading item 2");
            Item item3 = new Item("Cascading item 3");

            person.addItems(item1);
            person.addItems(item2);
            person.addItems(item3);

            session.save(person);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();

        }
    }
}
