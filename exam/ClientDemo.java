package com.klef.jfsd.exam;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            // Start a session
            session = factory.openSession();
            transaction = session.beginTransaction();

            // Update Department using HQL with positional parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            int rowsUpdated = session.createQuery(hql)
                                      .setParameter(1, "Updated Name")
                                      .setParameter(2, "Updated Location")
                                      .setParameter(3, 1)
                                      .executeUpdate();
             
            
            System.out.println(rowsUpdated + " record(s) updated.");

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
            factory.close();
        }
    }
}

